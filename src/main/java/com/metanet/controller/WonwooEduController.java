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
import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Error;
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

import com.metanet.domain.EduVO;
import com.metanet.domain.EmpListVO;
import com.metanet.domain.EmployeeVO;
import com.metanet.domain.FileDTO;
import com.metanet.domain.PageDTO;
import com.metanet.domain.PaginationDTO;
import com.metanet.service.WonwooEduService;

@Controller
@RequestMapping("/edu")
public class WonwooEduController {

	@Value("${spring.servlet.multipart.location}")
	String filePath;

	@Autowired
	WonwooEduService wonwooEduService;

	@GetMapping("/list")
	public String eduList(Model model) {
		model.addAttribute("eduList", wonwooEduService.eduList());
		model.addAttribute("title", "교육과정 전체 리스트");
		return "/admin/edu/list";
	}

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
		model.addAttribute("title", "교육과정 상세조회");

		return "/admin/edu/detail";
	}

	@GetMapping("/update")
	public String eduUpdateView(Model model, HttpServletRequest request) {
		int edu_no = Integer.parseInt(request.getParameter("edu_no"));
		model.addAttribute("eduVO", wonwooEduService.eduDetail(edu_no));
		System.out.println("getRefile : " + wonwooEduService.eduDetail(edu_no).getEduRefile());
		model.addAttribute("title", "교육과정 수정");
		return "/admin/edu/update";
	}

	@PostMapping("/update")
	public String eduUpdateAction(@ModelAttribute("eduVO") EduVO eduVO, @Valid EduVO eduVO2, Errors errors,
			@RequestParam MultipartFile uploadfile, FileDTO fileDTO, HttpServletRequest request, Model model)
			throws IllegalStateException, IOException {
		// System.out.println(eduVO.getEduRefile());

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
				// 사원등록 실패시, 입력 데이터를 유지
				model.addAttribute("eduVO", eduVO2);
				// 유효성 통과 못한 필드와 메시지를 핸들링
				Map<String, String> validatorResult = wonwooEduService.validateHandling(errors);
				for (String key : validatorResult.keySet()) {
					model.addAttribute(key, validatorResult.get(key));
				}
				return "/admin/edu/update";
			}
			wonwooEduService.eduUpdate(eduVO);
			return "redirect:/edu/detail?edu_no=" + Integer.parseInt(request.getParameter("eduNo"));
		} else {
			if (errors.hasErrors()) {
				// 사원등록 실패시, 입력 데이터를 유지
				model.addAttribute("eduVO", eduVO2);
				// 유효성 통과 못한 필드와 메시지를 핸들링
				Map<String, String> validatorResult = wonwooEduService.validateHandling(errors);
				for (String key : validatorResult.keySet()) {
					model.addAttribute(key, validatorResult.get(key));
				}
				return "/admin/edu/update";
			}
			wonwooEduService.eduUpdateNoModifyFile(eduVO);
			return "redirect:/edu/detail?edu_no=" + Integer.parseInt(request.getParameter("eduNo"));
		}
	}

	@GetMapping("/delete")
	public String eduDelete(HttpServletRequest request) {
		// 해당경로 이전 파일 삭제
		String eduRefile = request.getParameter("edu_refile");
		System.out.println("eduRefile");
		//System.out.println(eduVO.toString());
		if (eduRefile != null) {
			File file = new File(filePath + "\\" + eduRefile);
			// System.out.println("file : " + file);

			if (file.exists()) {
				file.delete(); // 파일 삭제
			}
		}
		int edu_no = Integer.parseInt(request.getParameter("edu_no"));
		wonwooEduService.eduDelete(edu_no);
		return "redirect:/edu/list";
	}

	@RequestMapping("/allocation")
	public String eduAllocation(Model model, HttpServletRequest request) {
		int pageNum = 1;
		String keyword = "";
		String searchContent = "";
		/* int edu_no = Integer.parseInt(request.getParameter("edu_no")); */

		System.out.println(request.getParameter("pageNum"));

		// 현재 페이지 넘버
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt((request.getParameter("pageNum")).trim());
		}
		model.addAttribute("tempPageNum", pageNum);

		// 키워드(작성자/작성내용 등)
		if (request.getParameter("keyword") != null && request.getParameter("keyword") != "") {
			keyword = request.getParameter("keyword");
		}

		if (request.getParameter("searchContent") != null && request.getParameter("searchContent") != "") {
			searchContent = request.getParameter("searchContent");
		}

		System.out.println(keyword);
		System.out.println(searchContent);
		// 페이지 관련 정보
		PageDTO pdto = new PageDTO(pageNum, keyword, searchContent);
		model.addAttribute("pageInfo", pdto);

		// 페이징 처리된 리스트(쿼리에서 쓰임/ 현재 페이지 넘버와 키워드 보내줌)
		List<EmpListVO> list = wonwooEduService.getPagingList(pdto);
		model.addAttribute("list", list);

		// 페이징 처리된 숫자그룹(뷰에서)
		int total = wonwooEduService.totalCount(pdto);
		PaginationDTO pageDto = new PaginationDTO(total, pdto);

		model.addAttribute("paging", pageDto);
		model.addAttribute("title", "교육 인원할당");
		return "/admin/edu/allocation";
	}

	@GetMapping("/history")
	public String eduEmpHistoyList(Model model, @RequestParam("empNo") String empNo) {
		if (empNo == null || empNo == "") {
			return "redirect:/signin";
		} else {
			System.out.println(Integer.parseInt(empNo));
			model.addAttribute("eduEmpHistoyList", wonwooEduService.getEduEmpHistroyList(Integer.parseInt(empNo)));
			return "/history";
		}
	}

	@PostMapping("/history")
	public String eduEmpHistoyListBykeyword(Model model, HttpServletRequest request,
			@SessionAttribute("sessionEmp") EmployeeVO empVO) {
		int empNo = empVO.getEmpNo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empNo", empNo);
		map.put("keyField", request.getParameter("keyField"));
		map.put("keyword", request.getParameter("keyword"));
		model.addAttribute("eduEmpHistoyList", wonwooEduService.getEduEmpHistroyListByKey(map));
		return "/history";
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

	@GetMapping("/addFile")
	public String eduAdd(Model model) {
		model.addAttribute("title", "교육 과정 추가");
		System.out.println("교육 추가 페이지 출력");
		return "/admin/edu/addFile";
	}

	@PostMapping("/addFile")
	public String eduAdd(@Valid EduVO eduVO, Errors errors, Model model, @RequestParam MultipartFile uploadfile,
			FileDTO fileDTO) throws IllegalStateException, IOException {
		if (!uploadfile.isEmpty()) {
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
				// 사원등록 실패시, 입력 데이터를 유지
				model.addAttribute("eduVO", eduVO);
				// 유효성 통과 못한 필드와 메시지를 핸들링
				Map<String, String> validatorResult = wonwooEduService.validateHandling(errors);
				for (String key : validatorResult.keySet()) {
					model.addAttribute(key, validatorResult.get(key));
				}
				return "/admin/edu/addFile";
			}
			wonwooEduService.eduAdd(eduVO);
			System.out.println("교육 과정 추가");
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
				return "/admin/edu/addFile";
			}
			wonwooEduService.eduAddNoFile(eduVO);
			System.out.println("교육 과정 추가");
			return "redirect:/edu/list";
		}
	}
}
