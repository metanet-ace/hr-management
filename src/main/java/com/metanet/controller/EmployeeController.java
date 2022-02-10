package com.metanet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.metanet.domain.EmployeeVO;
import com.metanet.persistence.EmployeeRepository;
import com.metanet.service.EmployeeServiceImpl;

@SessionAttributes("sessionEmp")
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl empService;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/admin/emp")
	public String userList(Model model, @PageableDefault(size = 3, sort = "empNo",
				direction = Sort.Direction.DESC ) Pageable pageable) {
		Page<EmployeeVO> emp = empService.getEmpList(pageable);
		
		int totalPages = emp.getTotalPages(); // 총 페이지 개수 
		int pageNumber = emp.getPageable().getPageNumber(); // 현재 페이지
		int pageBlock = 5; // 블럭의 수
		
		int startBlockPage = (pageNumber/pageBlock)*pageBlock+1;
		int endBlockPage = startBlockPage + pageBlock-1;
		endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("emplist", emp);
		model.addAttribute("title", "사원 전체 리스트");
		
		return "/admin/empList";
	}
	
	
	// 시큐리티 사용한 로그인 화면
	@GetMapping("/signin")
	public String loginView() {
		return "loginWithSecurity";
	}

	// 시큐리티 사용한 로그인 성공시	이동하는 화면
	@GetMapping("/loginSuccess")
	public String login() {
		return "redirect:/main";
	}
	
	// 엑세스 접근 권한이 없습니다 페이지로 이동
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}
	
	// 부트스트랩 이용한 전체 사원 리스트 출력
	@RequestMapping("/admin/emp2")
	public String getEmpListView(Model model) {
		model.addAttribute("emplist", empRepo.findAll());
		model.addAttribute("title", "사원 전체 리스트");
		return "admin/empList";
	}
	
	
}
