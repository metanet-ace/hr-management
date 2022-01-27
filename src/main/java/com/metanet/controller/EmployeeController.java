package com.metanet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.metanet.service.EmployeeServiceImpl;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl empService;
	
	@RequestMapping("/admin/emp")
	public String getEmpListView(Model model) {
		
		model.addAttribute("emplist", empService.getEmpList());
		model.addAttribute("title", "사원 전체 리스트");
		return "admin/empList";
	}
	
	
}
