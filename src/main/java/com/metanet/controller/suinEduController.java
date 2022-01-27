package com.metanet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metanet.domain.EduVO;
import com.metanet.service.suinEduServiceImpl;


@Controller
@RequestMapping("/edu")
public class suinEduController {
	@Autowired
	private suinEduServiceImpl service;
	
	@GetMapping("/add")
	public String eduAdd() {
		System.out.println("교육 추가 페이지 출력");
		return "/admin/edu/eduAdd";
	}
	
	@PostMapping("/add")
	public String eduAdd(@ModelAttribute("params") EduVO params ) {
		service.eduAdd(params);
		System.out.println("교육 과정 추가");
		return "index";
	}
}
