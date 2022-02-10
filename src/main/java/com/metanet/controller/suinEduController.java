package com.metanet.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.metanet.domain.EduVO;
import com.metanet.service.suinEduServiceImpl;


@Controller
@RequestMapping("/edu")
@Component
public class suinEduController {
	@Autowired
	private suinEduServiceImpl service;
	
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
		service.eduAttendance(null);
	}
}
