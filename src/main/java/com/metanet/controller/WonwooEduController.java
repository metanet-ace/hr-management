package com.metanet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metanet.domain.EduVO;
import com.metanet.domain.EmpListVO;
import com.metanet.domain.PageDTO;
import com.metanet.domain.PaginationDTO;
import com.metanet.service.WonwooEduService;

@Controller
@RequestMapping("/edu")
public class WonwooEduController {
	
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
		model.addAttribute("detail", wonwooEduService.eduDetail(edu_no));
		model.addAttribute("title", "교육과정 상세조회");
		return "/admin/edu/detail";
	}
	
	@GetMapping("/update")
	public String eduUpdateView(Model model, HttpServletRequest request) {
		int edu_no = Integer.parseInt(request.getParameter("edu_no"));
		model.addAttribute("detail", wonwooEduService.eduDetail(edu_no));
		model.addAttribute("title", "교육과정 수정");
		return "/admin/edu/update";
	}
	
	@PostMapping("/update")
	public String eduUpdateAction(EduVO eduVO, HttpServletRequest request) {
		String test = eduVO.toString();
		System.out.println(test);
		wonwooEduService.eduUpdate(eduVO);
		return "redirect:/edu/detail?edu_no="+Integer.parseInt(request.getParameter("eduNo"));
	}
	
	@GetMapping("/delete")
	public String eduDelete(HttpServletRequest request) {
		int edu_no = Integer.parseInt(request.getParameter("edu_no"));
		wonwooEduService.eduDelete(edu_no);
		return "redirect:/edu/list";
	}
	
	@RequestMapping("/allocation")
	public String eduAllocation(Model model, HttpServletRequest request) {
		int pageNum=1;
		String keyword = "";
		String searchContent = "";
		/* int edu_no = Integer.parseInt(request.getParameter("edu_no")); */
		
		
		System.out.println(request.getParameter("pageNum"));
		
		 // 현재 페이지 넘버 
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt((request.getParameter("pageNum")).trim());
		}
		model.addAttribute("tempPageNum", pageNum);
        
        // 키워드(작성자/작성내용 등)
        if(request.getParameter("keyword") != null && request.getParameter("keyword") != ""){
        	keyword = request.getParameter("keyword");	
		}
        
        if(request.getParameter("searchContent") != null && request.getParameter("searchContent") != ""){
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
	public String eduEmpHistoyList(Model model) {
		int empNo = 2299001;
		model.addAttribute("eduEmpHistoyList", wonwooEduService.getEduEmpHistroyList(empNo));
		return "/history";
	}
	
	@PostMapping("/history")
	public String eduEmpHistoyListBykeyword(Model model, HttpServletRequest request) {
		int empNo = 2299001;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empNo", empNo);
		map.put("keyField", request.getParameter("keyField"));
		map.put("keyword", request.getParameter("keyword"));
		model.addAttribute("eduEmpHistoyList", wonwooEduService.getEduEmpHistroyListByKey(map));
		return "/history";
	}

		
	}
