package com.metanet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.metanet.service.EmployeeServiceImpl;

@SessionAttributes("emp")
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl empService;

	// 시큐리티 사용한 로그인 화면
	@GetMapping("/signin")
	public String loginView() {
		return "loginWithSecurity";
	}

	// 시큐리티 사용한 로그인 
	@GetMapping("/loginSuccess")
	public String login() {
		return "redirect:/main";
//		int username = vo.getEmpNo();
//		String password = vo.getEmpPwd();
//		
//		EmployeeVO loginEmp = empService.login(username, password);
//		
//		if(loginEmp != null) {
//			// @ModelAttribute("이름")이랑 SessionAttributes("이름")이 같으면 
//			// 모델 정보를 자동으로 세션에 넣어준다.
//			model.addAttribute("emp", loginEmp);
//			return "redirect:/main";			
//		} else {
//			return "redirect:/signin";
//		}
	}
	
	// 엑세스 접근 권한이 없습니다 페이지로 이동
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}
	
	@RequestMapping("/admin/emp")
	public String getEmpListView(Model model) {
		
		model.addAttribute("emplist", empService.getEmpList());
		model.addAttribute("title", "사원 전체 리스트");
		return "admin/empList";
	}
	
	
}
