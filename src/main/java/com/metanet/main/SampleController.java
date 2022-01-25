package com.metanet.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {
	
	@RequestMapping("/emp")
	public String employeeList() {
		
		
		return "employeeList";
	}
	
	@RequestMapping("/admin/emp/")
	public String adminEmpList() {
		
		return "admin_employeeList";
	}
	
	
	@RequestMapping("/edu")
	public String eduList() {
		return "eduList";
	}
	
	@RequestMapping("/emp/{id}")
	public String updateOrDeleteInfo(@PathVariable("id") int id)	{
		
		
		return "updateresult";
		
	}
	
	
}
