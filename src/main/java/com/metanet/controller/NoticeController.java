package com.metanet.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;

import com.metanet.domain.EmployeeVO;
import com.metanet.domain.FileDTO;
import com.metanet.domain.LogVO;
import com.metanet.domain.NoticeVO;
import com.metanet.domain.PageDTO;
import com.metanet.domain.PaginationDTO;
import com.metanet.persistence.Util;
import com.metanet.service.WonwooEduService;

@Controller
public class NoticeController {
	
	@Autowired
	WonwooEduService service;
	
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
		List<NoticeVO> list = service.getPagingNoticeList(pdto);
		model.addAttribute("list", list);

		// 페이징 처리된 숫자그룹(뷰에서)
		int total = service.noticeTotalCount(pdto);
		PaginationDTO pageDto = new PaginationDTO(total, pdto);

		model.addAttribute("paging", pageDto);
		model.addAttribute("title", "공지사항 리스트");

		return "/notice";
	}
	
	// 공지사항 상세페이지
		@GetMapping("/noticeDetail")
		public String noticeDetail(Model model, HttpServletRequest request) {
			int noticeNo = Integer.parseInt(request.getParameter("notice_no"));
			NoticeVO noticeVO = service.noticeDetail(noticeNo);

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
		
		@GetMapping("/updateNotice")
		public String updateNotice(Model model, HttpServletRequest request) {
			int notice_no = Integer.parseInt(request.getParameter("notice_no"));
			model.addAttribute("noticeVO", service.noticeDetail(notice_no));
			System.out.println("getRefile : " + service.noticeDetail(notice_no).getNoticeRefile());
			model.addAttribute("title", "공지사항 정보수정");
			return "/noticeUpdate";
		}

		@PostMapping("/updateNotice")
		public String updateNoticeAction(@ModelAttribute("noticeVO") NoticeVO noticeVO, @Valid NoticeVO noticeVO2,
				Errors errors, @RequestParam MultipartFile uploadfile, FileDTO fileDTO, HttpServletRequest request,
				Model model, LogVO logVO) throws IllegalStateException, IOException {
			System.out.println(uploadfile);
			logVO.setLogIp(Util.getUserIp(request));
			logVO.setEmpNo(noticeVO.getEmpNo());
			logVO.setLogTarget("공지사항 수정");
			
			if (!uploadfile.isEmpty()) {
				System.out.println("noticeVO: " + noticeVO.getNoticeFile());
				if (noticeVO.getNoticeFile() != null) {
					
					String folderPath = request.getSession().getServletContext().getRealPath("resources");
					System.out.println("FolderPath : " + folderPath);
					String root = folderPath + "\\noticeFiles";

					File folder = new File(root);

					if (!folder.exists()) {
						folder.mkdirs();
					}
					
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
				System.out.println("newFileName: " + newFileName);
				uploadfile.transferTo(newFileName);

				String noticeRefile = newFileName.getName();
				noticeVO.setNoticeRefile(noticeRefile);

				if (errors.hasErrors()) {
					// 교육수정 실패시, 입력 데이터를 유지
					model.addAttribute("noticeVO", noticeVO2);
					// 유효성 통과 못한 필드와 메시지를 핸들링
					Map<String, String> validatorResult = service.validateHandling(errors);
					for (String key : validatorResult.keySet()) {
						model.addAttribute(key, validatorResult.get(key));
					}
					model.addAttribute("title", "교육 정보수정");
					return "/noticeUpdate";
				}
				model.addAttribute("title", "공지사항 정보수정");
				service.noticeUpdate(noticeVO);
				logVO.setLogDesc("공지사항 정보수정(파일O)");
				service.writeLog(logVO);
				return "redirect:./noticeDetail?notice_no=" + Integer.parseInt(request.getParameter("noticeNo"));

			} else {
				if (errors.hasErrors()) {
					// 교육수정 실패시, 입력 데이터를 유지
					model.addAttribute("noticeVO", noticeVO2);
					// 유효성 통과 못한 필드와 메시지를 핸들링
					Map<String, String> validatorResult = service.validateHandling(errors);
					for (String key : validatorResult.keySet()) {
						model.addAttribute(key, validatorResult.get(key));
					}
					model.addAttribute("title", "교육 정보수정");
					return "/noticeUpdate";
				}
				model.addAttribute("title", "공지사항 정보수정");
				service.noticeUpdateNoModifyFile(noticeVO);
				logVO.setLogDesc("공지사항 정보수정(파일X)");
				service.writeLog(logVO);
				return "redirect:./noticeDetail?notice_no=" + Integer.parseInt(request.getParameter("noticeNo"));
			}
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
					Map<String, String> validatorResult = service.validateHandling(errors);
					for (String key : validatorResult.keySet()) {
						model.addAttribute(key, validatorResult.get(key));
					}
					return "/noticeAdd";
				}
				service.noticeAdd(noticeVO);
				logVO.setLogDesc("공지사항 등록 성공(파일O)");
				service.writeLog(logVO);
				
				return "redirect:/notice";

			} else {
				if (errors.hasErrors()) {
					// 사원등록 실패시, 입력 데이터를 유지
					model.addAttribute("noticeVO", noticeVO);
					// 유효성 통과 못한 필드와 메시지를 핸들링
					Map<String, String> validatorResult = service.validateHandling(errors);
					for (String key : validatorResult.keySet()) {
						model.addAttribute(key, validatorResult.get(key));
					}
					return "/noticeAdd";
				}
				service.noticeAddNoFile(noticeVO);
				logVO.setLogDesc("공지사항 등록 성공(파일X)");
				service.writeLog(logVO);
				return "redirect:/notice";
			}	
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
			service.noticeDelete(notice_no);
			logVO.setLogDesc("공지사항 삭제 성공");
			service.writeLog(logVO);
			return "redirect:/notice";
		}
}
