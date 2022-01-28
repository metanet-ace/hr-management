package com.metanet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metanet.service.WonwooEduService;

@Controller
@RequestMapping("/admin/edu")
public class WonwooEduController {
	
	@Autowired
	WonwooEduService wonwooEduService;
	
	@GetMapping("/eduList")
	public String eduList(Model model) {
		model.addAttribute("eduList", wonwooEduService.eduList());
		model.addAttribute("title", "교육계획/과정 전체 리스트");
		return "/admin/edu/eduList";
	}
		
	
}
