package com.metanet.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.metanet.domain.EduHistoryVO;
import com.metanet.domain.EduVO;
import com.metanet.service.WonwooEduService;
import com.metanet.service.suinEduService;


@Controller
@RequestMapping("/edu")
@Component
public class suinEduController {
	@Autowired
	private suinEduService service;
	
	@Autowired
	WonwooEduService wonwooEduService;
	
	@GetMapping("/add")
	public String eduAdd(Model model) {
		model.addAttribute("title", "교육 과정 추가");
		System.out.println("교육 추가 페이지 출력");
		return "/admin/edu/add";
	}
	
	@PostMapping("/add")
	public String eduAdd(@ModelAttribute("params") EduVO params) {
		service.eduAdd(params);
		System.out.println("교육 과정 추가");
		return "redirect:/edu/list";
	}
	
	
	@Scheduled(cron="0 0 0 * * ?", zone="Asia/Seoul")
	public void schedulerTest() throws ParseException {
		System.out.println("Scheduler Test..." + new Date());
		service.eduAttendance();
	}
	
	@GetMapping("/allocation2/{eduNo}")
	public String eduAllocation(@PathVariable int eduNo,Model model) {
		model.addAttribute("empList", wonwooEduService.empList());
		model.addAttribute("title", "교육 인원할당");
		model.addAttribute("eduNo", eduNo);
	return "/admin/edu/allocationCheckbox";
	}
	
	@PostMapping("/allocation2")
	public String eduAllocCheck(HttpServletRequest request) {
		System.out.println(">>>>>>>>>>>> eduAllocCheck");
		for(String s : request.getParameterValues("empNo")) {
			System.out.println(s);
		}
		System.out.println(request.getParameterValues("empNo").getClass().getName());
		return "redirect:/edu/admin/history";
	}
	
	@GetMapping("/admin/history")
	public String eduHistoryList(Model model) {
		model.addAttribute("eduHistoryList", service.getEduHistoryList());
		return "/admin/edu/history";
	}
	
	@PostMapping("/admin/history")
	public String eduHistoryListByKeyword(Model model, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyField", request.getParameter("keyField"));
		map.put("keyword", request.getParameter("keyword"));
		model.addAttribute("eduHistoryList", service.getEduHistoryListByKey(map));
		return "/admin/edu/history";
	}
}
