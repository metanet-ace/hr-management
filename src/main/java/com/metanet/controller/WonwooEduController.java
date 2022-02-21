package com.metanet.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.metanet.domain.EduVO;
import com.metanet.domain.EmployeeVO;
import com.metanet.domain.FileDTO;
import com.metanet.domain.LogVO;
import com.metanet.domain.NoticeVO;
import com.metanet.domain.PageDTO;
import com.metanet.domain.PaginationDTO;
import com.metanet.persistence.Util;
import com.metanet.service.WonwooEduService;

@Controller
@RequestMapping("/edu")
public class WonwooEduController {

	@Value("${spring.servlet.multipart.location}")
	String filePath;

	@Autowired
	WonwooEduService wonwooEduService;

	@RequestMapping("/notice")
	public String noticeList(Model model, HttpServletRequest request) {
		int pageNum = 1;
		String keyField = "";
		String keyword = "";

		// 현재 페이지 넘버
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt((request.getParameter("pageNum")).trim());
		}
		model.addAttribute("tempPageNum", pageNum);

		// 키워드(작성자/작성내용 등)
		if (request.getParameter("keyField") != null && request.getParameter("keyField") != "") {
			keyField = request.getParameter("keyField");
		}

		if (request.getParameter("keyword") != null && request.getParameter("keyword") != "") {
			keyword = request.getParameter("keyword");
		}

		// 페이지 관련 정보
		PageDTO pdto = new PageDTO(pageNum, keyField, keyword);
		model.addAttribute("pageInfo", pdto);

		// 페이징 처리된 리스트(쿼리에서 쓰임/ 현재 페이지 넘버와 키워드 보내줌)
		List<NoticeVO> list = wonwooEduService.getPagingNoticeList(pdto);
		model.addAttribute("list", list);

		// 페이징 처리된 숫자그룹(뷰에서)
		int total = wonwooEduService.noticeTotalCount(pdto);
		PaginationDTO pageDto = new PaginationDTO(total, pdto);

		model.addAttribute("paging", pageDto);
		model.addAttribute("title", "공지사항 리스트");

		return "/notice";
	}
	
	@RequestMapping("/log")
	public String logList(Model model, HttpServletRequest request) {
		int pageNum = 1;
		String keyField = "";
		String keyword = "";

		// 현재 페이지 넘버
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt((request.getParameter("pageNum")).trim());
		}
		model.addAttribute("tempPageNum", pageNum);

		// 키워드(작성자/작성내용 등)
		if (request.getParameter("keyField") != null && request.getParameter("keyField") != "") {
			keyField = request.getParameter("keyField");
		}

		if (request.getParameter("keyword") != null && request.getParameter("keyword") != "") {
			keyword = request.getParameter("keyword");
		}

		// 페이지 관련 정보
		PageDTO pdto = new PageDTO(pageNum, keyField, keyword);
		model.addAttribute("pageInfo", pdto);

		// 페이징 처리된 리스트(쿼리에서 쓰임/ 현재 페이지 넘버와 키워드 보내줌)
		List<LogVO> list = wonwooEduService.getPagingLogList(pdto);
		model.addAttribute("list", list);

		// 페이징 처리된 숫자그룹(뷰에서)
		int total = wonwooEduService.logTotalCount(pdto);
		PaginationDTO pageDto = new PaginationDTO(total, pdto);

		model.addAttribute("paging", pageDto);
		model.addAttribute("title", "로그 리스트");

		return "/admin/log";
	}

	// 인사팀용 교육과정 상세페이지
	@GetMapping("/detail")
	public String eduDetail(Model model, HttpServletRequest request) {
		int edu_no = Integer.parseInt(request.getParameter("edu_no"));
		EduVO eduVO = wonwooEduService.eduDetail(edu_no);

		if (eduVO.getEduRefile() != null) {
			String eduRefile = eduVO.getEduRefile();
			System.out.println(eduRefile);
			String[] eduRefileSplitUuid = eduRefile.split("_");
			System.out.println(eduRefileSplitUuid[0]);
			model.addAttribute("uuid", eduRefileSplitUuid[0]);
		}

		model.addAttribute("detail", eduVO);
		model.addAttribute("title", "교육 상세조회");

		return "/admin/edu/detail";
	}

	// 사원용 교육과정 상세페이지
	@GetMapping("/eduEmpDetail")
	public String eduEmpDetail(Model model, HttpServletRequest request) {
		int edu_no = Integer.parseInt(request.getParameter("edu_no"));
		EduVO eduVO = wonwooEduService.eduDetail(edu_no);

		if (eduVO.getEduRefile() != null) {
			String eduRefile = eduVO.getEduRefile();
			System.out.println(eduRefile);
			String[] eduRefileSplitUuid = eduRefile.split("_");
			System.out.println(eduRefileSplitUuid[0]);
			model.addAttribute("uuid", eduRefileSplitUuid[0]);
		}

		model.addAttribute("detail", eduVO);
		model.addAttribute("title", "교육 상세조회");

		return "/admin/empEduDetail";
	}

	// 공지사항 상세페이지
	@GetMapping("/noticeDetail")
	public String noticeDetail(Model model, HttpServletRequest request) {
		int noticeNo = Integer.parseInt(request.getParameter("notice_no"));
		NoticeVO noticeVO = wonwooEduService.noticeDetail(noticeNo);

		if (noticeVO.getNoticeRefile() != null) {
			String noticeRefile = noticeVO.getNoticeRefile();
			System.out.println(noticeRefile);
			String[] noticeRefileSplitUuid = noticeRefile.split("_");
			System.out.println(noticeRefileSplitUuid[0]);
			model.addAttribute("uuid", noticeRefileSplitUuid[0]);
		}

		model.addAttribute("detail", noticeVO);
		model.addAttribute("title", "공지사항 상세조회");

		return "/noticeDetail";
	}
	
	//사원용 교육과정 업데이트 페이지
	@GetMapping("/update")
	public String eduUpdateView(Model model, HttpServletRequest request) {
		int edu_no = Integer.parseInt(request.getParameter("edu_no"));
		model.addAttribute("eduVO", wonwooEduService.eduDetail(edu_no));
		System.out.println("getRefile : " + wonwooEduService.eduDetail(edu_no).getEduRefile());
		model.addAttribute("title", "교육 정보수정");
		return "/admin/edu/update";
	}
	
	//사원용 교육과정 업데이트 수행 페이지
	@PostMapping("/update")
	public String eduUpdateAction(@ModelAttribute("eduVO") EduVO eduVO, @Valid EduVO eduVO2, Errors errors,
			@RequestParam MultipartFile uploadfile, FileDTO fileDTO,
			HttpServletRequest request, Model model, LogVO logVO)
			throws IllegalStateException, IOException {
		
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		logVO.setLogIp(Util.getUserIp(request));
		logVO.setEmpNo(empNo);
		logVO.setLogTarget("교육과정 수정");
		
		// 새로운 파일이 들어온다면
		if (!uploadfile.isEmpty()) {

			// 해당경로 이전 파일 삭제
			if (eduVO.getEduRefile() != null) {
				File file = new File(filePath + "\\" + eduVO.getEduRefile());

				// System.out.println("file : " + file);

				if (file.exists()) {
					file.delete(); // 파일 삭제
				}
				eduVO.setEduFile(null);
				eduVO.setEduRefile(null);
			}

			String eduFile = uploadfile.getOriginalFilename();
			System.out.println(eduFile);
			eduVO.setEduFile(eduFile);

			FileDTO dto = new FileDTO(UUID.randomUUID().toString(), uploadfile.getOriginalFilename(),
					uploadfile.getContentType());
			File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());

			uploadfile.transferTo(newFileName);

			String eduRefile = newFileName.getName();
			System.out.println(eduRefile);
			eduVO.setEduRefile(eduRefile);

			if (errors.hasErrors()) {
				// 교육수정 실패시, 입력 데이터를 유지
				model.addAttribute("eduVO", eduVO2);
				// 유효성 통과 못한 필드와 메시지를 핸들링
				Map<String, String> validatorResult = wonwooEduService.validateHandling(errors);
				for (String key : validatorResult.keySet()) {
					model.addAttribute(key, validatorResult.get(key));
				}
				model.addAttribute("title", "교육 정보수정");
				return "/admin/edu/update";
			}
			model.addAttribute("title", "교육 정보수정");
			wonwooEduService.eduUpdate(eduVO);
			logVO.setLogDesc("교육 정보수정 파일(O)");
			wonwooEduService.writeLog(logVO);
			return "redirect:/edu/detail?edu_no=" + Integer.parseInt(request.getParameter("eduNo"));
		} else {
			if (errors.hasErrors()) {
				// 교육수정 실패시, 입력 데이터를 유지
				model.addAttribute("eduVO", eduVO2);
				// 유효성 통과 못한 필드와 메시지를 핸들링
				Map<String, String> validatorResult = wonwooEduService.validateHandling(errors);
				for (String key : validatorResult.keySet()) {
					model.addAttribute(key, validatorResult.get(key));
				}
				model.addAttribute("title", "교육 정보수정");
				return "/admin/edu/update";
			}
			model.addAttribute("title", "교육 정보수정");
			wonwooEduService.eduUpdateNoModifyFile(eduVO);
			logVO.setLogDesc("교육 정보수정 파일(X)");
			wonwooEduService.writeLog(logVO);
			return "redirect:/edu/detail?edu_no=" + Integer.parseInt(request.getParameter("eduNo"));
		}
	}

	@GetMapping("/updateNotice")
	public String updateNotice(Model model, HttpServletRequest request) {
		int notice_no = Integer.parseInt(request.getParameter("notice_no"));
		model.addAttribute("noticeVO", wonwooEduService.noticeDetail(notice_no));
		System.out.println("getRefile : " + wonwooEduService.noticeDetail(notice_no).getNoticeRefile());
		model.addAttribute("title", "공지사항 정보수정");
		return "/noticeUpdate";
	}

	@PostMapping("/updateNotice")
	public String updateNoticeAction(@ModelAttribute("noticeVO") NoticeVO noticeVO, @Valid NoticeVO noticeVO2,
			Errors errors, @RequestParam MultipartFile uploadfile, FileDTO fileDTO, HttpServletRequest request,
			Model model, LogVO logVO) throws IllegalStateException, IOException {
		
		logVO.setLogIp(Util.getUserIp(request));
		logVO.setEmpNo(noticeVO.getEmpNo());
		logVO.setLogTarget("공지사항 수정");
		
		if (!uploadfile.isEmpty()) {
			if (noticeVO.getNoticeFile() != null) {
				String path = request.getSession().getServletContext().getRealPath("resources") + "\\noticeFiles";
				System.out.println("Path : " + path);

				File file = new File(path + noticeVO.getNoticeRefile());

				if (file.exists()) {
					file.delete(); // 파일 삭제
				}
				noticeVO.setNoticeFile(null);
				noticeVO.setNoticeRefile(null);
			}

			String noticeFile = uploadfile.getOriginalFilename();
			noticeVO.setNoticeFile(noticeFile);

			FileDTO dto = new FileDTO(UUID.randomUUID().toString(), uploadfile.getOriginalFilename(),
					uploadfile.getContentType());
			File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());

			uploadfile.transferTo(newFileName);

			String noticeRefile = newFileName.getName();
			noticeVO.setNoticeRefile(noticeRefile);

			if (errors.hasErrors()) {
				// 교육수정 실패시, 입력 데이터를 유지
				model.addAttribute("noticeVO", noticeVO2);
				// 유효성 통과 못한 필드와 메시지를 핸들링
				Map<String, String> validatorResult = wonwooEduService.validateHandling(errors);
				for (String key : validatorResult.keySet()) {
					model.addAttribute(key, validatorResult.get(key));
				}
				model.addAttribute("title", "교육 정보수정");
				return "/noticeUpdate";
			}
			model.addAttribute("title", "공지사항 정보수정");
			wonwooEduService.noticeUpdate(noticeVO);
			logVO.setLogDesc("공지사항 정보수정(파일O)");
			wonwooEduService.writeLog(logVO);
			return "redirect:./noticeDetail?notice_no=" + Integer.parseInt(request.getParameter("noticeNo"));

		} else {
			if (errors.hasErrors()) {
				// 교육수정 실패시, 입력 데이터를 유지
				model.addAttribute("noticeVO", noticeVO2);
				// 유효성 통과 못한 필드와 메시지를 핸들링
				Map<String, String> validatorResult = wonwooEduService.validateHandling(errors);
				for (String key : validatorResult.keySet()) {
					model.addAttribute(key, validatorResult.get(key));
				}
				model.addAttribute("title", "교육 정보수정");
				return "/noticeUpdate";
			}
			model.addAttribute("title", "공지사항 정보수정");
			wonwooEduService.noticeUpdateNoModifyFile(noticeVO);
			logVO.setLogDesc("공지사항 정보수정(파일X)");
			wonwooEduService.writeLog(logVO);
			return "redirect:./noticeDetail?notice_no=" + Integer.parseInt(request.getParameter("noticeNo"));
		}
	}
	
	//인사팀 교육과정 삭제
	@GetMapping("/delete")
	public String eduDelete(HttpServletRequest request, LogVO logVO) {
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		logVO.setLogIp(Util.getUserIp(request));
		logVO.setEmpNo(empNo);
		logVO.setLogTarget("공지사항 삭제");
		
		// 해당경로 이전 파일 삭제
		String eduRefile = request.getParameter("edu_refile");
		System.out.println("eduRefile");
		// System.out.println(eduVO.toString());
		if (eduRefile != null) {
			File file = new File(filePath + "\\" + eduRefile);
			// System.out.println("file : " + file);

			if (file.exists()) {
				file.delete(); // 파일 삭제
			}
		}
		int edu_no = Integer.parseInt(request.getParameter("edu_no"));
		wonwooEduService.eduDelete(edu_no);
		logVO.setLogDesc("공지사항 삭제 성공");
		wonwooEduService.writeLog(logVO);
		return "redirect:/edu/list";
	}
	
	@GetMapping("/deleteNotice")
	public String deleteNotice(HttpServletRequest request, LogVO logVO) {
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		logVO.setLogIp(Util.getUserIp(request));
		logVO.setEmpNo(empNo);
		logVO.setLogTarget("공지사항 삭제");
		
		// 해당경로 이전 파일 삭제
		String noticeRefile = request.getParameter("notice_refile");
		System.out.println(noticeRefile);
		if (noticeRefile != null) {
			String path = request.getSession().getServletContext().getRealPath("resources");
			System.out.println("Path : " + path);
			String root = path + "\\noticeFiles";

			File file = new File(root + "\\" + noticeRefile);

			if (file.exists()) {
				file.delete(); // 파일 삭제
			}
		}
		int notice_no = Integer.parseInt(request.getParameter("notice_no"));
		wonwooEduService.noticeDelete(notice_no);
		logVO.setLogDesc("공지사항 삭제 성공");
		wonwooEduService.writeLog(logVO);
		return "redirect:/edu/notice";
	}

	@RequestMapping("/history")
	public String eduEmpHistoyList(Model model, @RequestParam("empNo") String empNo, HttpServletRequest request) {
		if (empNo == null || empNo == "") {
			return "redirect:/signin";
		} else {
			int pageNum = 1;
			String keyField = "";
			String keyword = "";

			System.out.println(request.getParameter("pageNum"));

			// 현재 페이지 넘버
			if (request.getParameter("pageNum") != null) {
				pageNum = Integer.parseInt((request.getParameter("pageNum")).trim());
			}
			model.addAttribute("tempPageNum", pageNum);

			// 키워드(작성자/작성내용 등)
			if (request.getParameter("keyField") != null && request.getParameter("keyField") != "") {
				keyField = request.getParameter("keyField");
			}

			if (request.getParameter("keyword") != null && request.getParameter("keyword") != "") {
				keyword = request.getParameter("keyword");
			}

			System.out.println(keyField);
			System.out.println(keyword);
			// 페이지 관련 정보
			PageDTO pdto = new PageDTO(pageNum, keyField, keyword);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("startNum", pdto.getStartNum());
			map.put("endNum", pdto.getEndNum());
			map.put("keyword", pdto.getkeyword());
			map.put("empNo", Integer.parseInt(empNo));
			map.put("keyField", pdto.getkeyField());
			model.addAttribute("pageInfo", map);

			// 페이징 처리된 리스트(쿼리에서 쓰임/ 현재 페이지 넘버와 키워드 보내줌)
			List<EduVO> list = wonwooEduService.getPagingEmpEduList(map);
			model.addAttribute("list", list);

			// 페이징 처리된 숫자그룹(뷰에서)
			int total = wonwooEduService.EmpEduTotalCount(map);
			PaginationDTO pageDto = new PaginationDTO(total, pdto);

			model.addAttribute("paging", pageDto);
			model.addAttribute("title", "내 교육 정보");

			return "/history";
		}
	}

	@GetMapping("/download")
	public ResponseEntity<Resource> download(@ModelAttribute FileDTO dto) throws IOException {

		Path path = Paths.get(filePath + "/" + dto.getUuid() + "_" + dto.getFileName());
		String contentType = Files.probeContentType(path);

		HttpHeaders headers = new HttpHeaders();

		headers.setContentDisposition(
				ContentDisposition.builder("attachment").filename(dto.getFileName(), StandardCharsets.UTF_8).build());
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);

		Resource resource = new InputStreamResource(Files.newInputStream(path));
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);

	}

	@GetMapping("/downloadNoticeFile")
	public ResponseEntity<Resource> downloadNoticeFile(@ModelAttribute FileDTO dto, HttpServletRequest request)
			throws IOException {
		String path = request.getSession().getServletContext().getRealPath("resources");
		System.out.println("Path : " + path);
		String root = path + "\\noticeFiles";

		Path Realpath = Paths.get(root + "/" + dto.getUuid() + "_" + dto.getFileName());
		String contentType = Files.probeContentType(Realpath);

		HttpHeaders headers = new HttpHeaders();

		headers.setContentDisposition(
				ContentDisposition.builder("attachment").filename(dto.getFileName(), StandardCharsets.UTF_8).build());
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);

		Resource resource = new InputStreamResource(Files.newInputStream(Realpath));
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);

	}

	@GetMapping("/add")
	public String eduAdd(Model model) {
		model.addAttribute("title", "교육 등록");
		System.out.println("교육 추가 페이지 출력");
		return "/admin/edu/add";
	}

	@PostMapping("/add")
	public String eduAdd(@Valid EduVO eduVO, Errors errors, Model model, @RequestParam MultipartFile uploadfile,
			FileDTO fileDTO,@RequestParam("empNo") String empNo, LogVO logVO, HttpServletRequest request) throws IllegalStateException, IOException {
		
		logVO.setLogIp(Util.getUserIp(request));
		logVO.setEmpNo(Integer.parseInt(empNo));
		logVO.setLogTarget("교육 과정 등록");
		
		if (!uploadfile.isEmpty()) {
			String eduFile = uploadfile.getOriginalFilename();
			eduVO.setEduFile(eduFile);

			FileDTO dto = new FileDTO(UUID.randomUUID().toString(), uploadfile.getOriginalFilename(),
					uploadfile.getContentType());
			File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());

			uploadfile.transferTo(newFileName);

			String eduRefile = newFileName.getName();
			eduVO.setEduRefile(eduRefile);

			if (errors.hasErrors()) {
				// 사원등록 실패시, 입력 데이터를 유지
				model.addAttribute("eduVO", eduVO);
				// 유효성 통과 못한 필드와 메시지를 핸들링
				Map<String, String> validatorResult = wonwooEduService.validateHandling(errors);
				for (String key : validatorResult.keySet()) {
					model.addAttribute(key, validatorResult.get(key));
				}
				return "/admin/edu/add";
			}
			wonwooEduService.eduAdd(eduVO);
			logVO.setLogDesc("교육과정 등록 성공(파일O)");
			wonwooEduService.writeLog(logVO);
			return "redirect:/edu/list";

		} else {
			if (errors.hasErrors()) {
				// 사원등록 실패시, 입력 데이터를 유지
				model.addAttribute("eduVO", eduVO);
				// 유효성 통과 못한 필드와 메시지를 핸들링
				Map<String, String> validatorResult = wonwooEduService.validateHandling(errors);
				for (String key : validatorResult.keySet()) {
					model.addAttribute(key, validatorResult.get(key));
				}
				return "/admin/edu/add";
			}
			wonwooEduService.eduAddNoFile(eduVO);
			logVO.setLogDesc("교육과정 등록 성공(파일X)");
			wonwooEduService.writeLog(logVO);
			return "redirect:/edu/list";
		}
	}

	@GetMapping("/noticeAdd")
	public String noticeAdd(Model model, @SessionAttribute("sessionEmp") EmployeeVO emp) {
		if(emp.getDept().getDeptNo() != 1) {
			return "redirect:/signin";
		}
		model.addAttribute("title", "공지사항 등록");
		return "/noticeAdd";
	}

	@PostMapping("/noticeAdd")
	public String noticeAdd(@Valid NoticeVO noticeVO, Errors errors, Model model,
			@RequestParam MultipartFile uploadfile, HttpServletRequest request, @RequestParam("empNo") String empNo,
			@RequestParam("empName") String empName, FileDTO fileDTO, LogVO logVO, @SessionAttribute("sessionEmp") EmployeeVO emp) {
		if(emp.getDept().getDeptNo() != 1) {
			return "redirect:/signin";
		}
		noticeVO.setEmpNo(Integer.parseInt(empNo));
		noticeVO.setNoticeWriter(empName);
		
		logVO.setLogIp(Util.getUserIp(request));
		logVO.setEmpNo(Integer.parseInt(empNo));
		logVO.setLogTarget("공지사항 등록");

		if (!uploadfile.isEmpty()) {
			// 저장할 경로 가져오기
			String path = request.getSession().getServletContext().getRealPath("resources");
			System.out.println("Path : " + path);
			String root = path + "\\noticeFiles";

			File file = new File(root);

			if (!file.exists()) {
				file.mkdirs();
			}

			String noticeFile = uploadfile.getOriginalFilename();
			noticeVO.setNoticeFile(noticeFile);

			FileDTO dto = new FileDTO(UUID.randomUUID().toString(), noticeFile, uploadfile.getContentType());
			File newFileName = new File(dto.getUuid() + "_" + dto.getFileName());
			File changeFile = new File(root + "\\" + newFileName.getName());

			try {
				uploadfile.transferTo(changeFile);
				String noticeRefile = newFileName.getName();
				noticeVO.setNoticeRefile(noticeRefile);

				System.out.println("파일 업로드 성공");
			} catch (IllegalStateException | IOException e) {
				System.out.println("파일 업로드 실패");
				e.printStackTrace();
			}

			if (errors.hasErrors()) {
				// 공지사항 등록 실패시, 입력 데이터를 유지
				model.addAttribute("noticeVO", noticeVO);
				// 유효성 통과 못한 필드와 메시지를 핸들링
				Map<String, String> validatorResult = wonwooEduService.validateHandling(errors);
				for (String key : validatorResult.keySet()) {
					model.addAttribute(key, validatorResult.get(key));
				}
				return "/noticeAdd";
			}
			wonwooEduService.noticeAdd(noticeVO);
			logVO.setLogDesc("공지사항 등록 성공(파일O)");
			wonwooEduService.writeLog(logVO);
			
			return "redirect:/edu/notice";

		} else {
			if (errors.hasErrors()) {
				// 사원등록 실패시, 입력 데이터를 유지
				model.addAttribute("noticeVO", noticeVO);
				// 유효성 통과 못한 필드와 메시지를 핸들링
				Map<String, String> validatorResult = wonwooEduService.validateHandling(errors);
				for (String key : validatorResult.keySet()) {
					model.addAttribute(key, validatorResult.get(key));
				}
				return "/noticeAdd";
			}
			wonwooEduService.noticeAddNoFile(noticeVO);
			logVO.setLogDesc("공지사항 등록 성공(파일X)");
			wonwooEduService.writeLog(logVO);
			return "redirect:/edu/notice";
		}	
	}
	//사원 교육 과정 달력
		@GetMapping("/empEduCalendar")
		public String eduCalendar(Model model, @SessionAttribute("sessionEmp") EmployeeVO emp) {
			model.addAttribute("title", "교육 일정 달력");
			model.addAttribute("calList", wonwooEduService.empEduCalendarList(emp.getEmpNo()));
			return "/empEduCalendar";
		}
	
}
