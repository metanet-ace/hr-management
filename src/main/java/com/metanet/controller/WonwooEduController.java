package com.metanet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metanet.service.WonwooEduService;

@Controller
@RequestMapping("/edu")
public class WonwooEduController {
	
	@Autowired
	WonwooEduService wonwooEduService;
	
	@GetMapping("/list")
	public String eduList() {
		return "eduList";
	}
		
	
}
