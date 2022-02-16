package com.metanet.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.metanet.domain.EmpHistoryVO;
import com.metanet.domain.EmployeeVO;
import com.metanet.domain.SearchDTO;
import com.metanet.service.EmployeeServiceImpl;

@SessionAttributes("sessionEmp")
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl empService;
	
	@Autowired
	suinEduController batchCtrl;
	
	@GetMapping("/admin/emp")
	public String userList(Model model, @PageableDefault(size = 3, sort = "empNo",
				direction = Sort.Direction.DESC ) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field, 
			@RequestParam(required = false, defaultValue = "") String word) {
		// 검색 조건 없을 시 
		Page<EmployeeVO> emp = empService.getEmpList(pageable);
		
		// 필드명에 따른 로직 처리	
		if(field.equals("deptName")) {
			emp = empService.getEmpListWithDept(word, pageable);
		} else if (field.equals("posName")){
			emp = empService.getEmpListWithPos(word, pageable);
		} else if (field.equals("empName")) {
			emp = empService.getEmpListWithName(word, pageable);
		}
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
	
	// 인사 이동 컨트롤러	
	@PostMapping("/admin/hr")
	@ResponseBody
	public ResponseEntity<String> updateHumanResource(@RequestBody HashMap<String, Object> map) {
		int deptNo = 0;
		int posNo = 0;
		
		String reason = (String) map.get("reason");
		Object targetEmpList = map.get("targetEmps");
		 
		if(map.get("deptData") != "") {
			deptNo = Integer.parseInt((String) map.get("deptData"));			
		}
		if(map.get("posData") != "") {
			posNo = Integer.parseInt((String) map.get("posData"));
		}
		
		System.out.println("========================================");
		System.out.println(targetEmpList.toString() + "  변경하려는 부서: " + deptNo + " 변경하려는 직급: " + posNo);
		System.out.println("========================================");
		
		for(Object result : (List) targetEmpList) {
			int targetNo = Integer.parseInt((String) result);
			if(deptNo != 0 && posNo != 0) {
				empService.updateEmpDeptAndPos(targetNo, deptNo, posNo, reason);
			} else if(deptNo != 0 && posNo == 0){
				empService.updateEmpDept(targetNo, deptNo, reason);
			} else if(deptNo == 0 && posNo != 0) {
				empService.updateEmpPos(targetNo, posNo, reason);
			} else {
				return new ResponseEntity<String>("오류 발생", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	} 
	
	// 퇴사자 처리 컨트롤러
	@PostMapping("/admin/retire")
	@ResponseBody
	public ResponseEntity<String> updateRetire(@RequestBody HashMap<String, Object> map){
		String retireReason = (String) map.get("retireReason");
		Object targetEmpList = map.get("targetEmps");
		
		for(Object result : (List) targetEmpList) {
			int targetNo = Integer.parseInt((String) result);
			empService.updateRetire(targetNo, retireReason);
		}
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	
	@GetMapping("/admin/emp/history")
	public String empHistoryListView() {
		return "/admin/empHistory";
	}
	
	@PostMapping(value = "/admin/emp/history/{page}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Page<EmpHistoryVO>> empHistoryList(@PathVariable("page") int pageNum, @RequestBody HashMap<String, String> data) {
		int pageSize = 0;
		if(data.get("pageSize") != null) {
			pageSize = Integer.parseInt(data.get("pageSize"));
		} else {
			pageSize = 5; // 기본값
		}
		Pageable pageable = PageRequest.of(pageNum-1, pageSize, Sort.Direction.DESC, "empHisno");
		
		Page<EmpHistoryVO> empHistoryPage = empService.getEmpHistoryList(data, pageable);
		
		return new ResponseEntity<>(empHistoryPage, HttpStatus.OK);
	}
	
	// 시큐리티 사용한 로그인 화면
	@GetMapping("/signin")
	public String loginView() {
		return "loginWithSecurity";
	}

	// 시큐리티 사용한 로그인 성공시	이동하는 화면
	@GetMapping("/loginSuccess")
	public String login(Authentication authentication, Model model) {
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	    EmployeeVO emp = empService.getLoginedEmp(Integer.parseInt(userDetails.getUsername()));
		model.addAttribute("sessionEmp", emp );
		try { 
			batchCtrl.schedulerTest();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:/main";
	}
	
	// 엑세스 접근 권한이 없습니다 페이지로 이동
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}
	
}
