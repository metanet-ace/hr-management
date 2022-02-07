package com.metanet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metanet.domain.EduVO;
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
	
	
		
	
}
