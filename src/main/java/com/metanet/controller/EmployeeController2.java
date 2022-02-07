package com.metanet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.metanet.domain.EmployeeVO2;
import com.metanet.service.EmployeeService2;

@Controller
public class EmployeeController2 {
	
	@Autowired
	EmployeeService2 service;
	
	//로그인페이지 이동(공통)
	@GetMapping("/main2")
	public String loginPage() {
		return "login";
	}
	
	//로그인
	@PostMapping("/emp/login")
	public String login(EmployeeVO2 emp, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		EmployeeVO2 loginEmp = service.selectLogin(emp);
		System.out.println("++++++" + emp);
		System.out.println("오류잡아줘" + loginEmp);
		
		if(loginEmp != null) {
			session.setAttribute("loginEmp", loginEmp);
			return "redirect:/main";
		}else {
			return "redirect:/eduList";
		}
	}
	
	//사원등록페이지 이동(인사팀)
	@GetMapping("/admin/emp/insertEmpPage")
	public String insertEmployeePage() {
		return "/admin/insertEmp";
	}
	
//	//사원등록하기(인사팀)
//	@PostMapping("/admin/emp/insertEmp")
//	public String insertEmployee(EmployeeVO2 emp, HttpServletRequest request, )
//	
	
	
	
	
	
	
	
	
	
	
}
