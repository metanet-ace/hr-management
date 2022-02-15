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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metanet.domain.EduHistoryVO;
import com.metanet.domain.EduVO;
import com.metanet.domain.EmpListVO;
import com.metanet.domain.PageDTO;
import com.metanet.domain.PaginationDTO;
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
	public String eduAdd(Model model) throws ParseException {
		model.addAttribute("title", "교육 과정 추가");
		System.out.println("교육 추가 페이지 출력");
		schedulerTest();
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
	public String eduAllocation(@PathVariable("eduNo") int eduNo, 
								Model model, HttpServletRequest request) {	
		int pageNum=1;
		String keyword = "";
		String searchContent = "";
		/* int edu_no = Integer.parseInt(request.getParameter("edu_no")); */
		
		
		System.out.println(request.getParameter("pageNum"));
		
		 // 현재 페이지 넘버 
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt((request.getParameter("pageNum")).trim());
		}
		model.addAttribute("tempPageNum", pageNum);
        
        // 키워드(작성자/작성내용 등)
        if(request.getParameter("keyword") != null && request.getParameter("keyword") != ""){
        	keyword = request.getParameter("keyword");	
		}
        
        if(request.getParameter("searchContent") != null && request.getParameter("searchContent") != ""){
        	searchContent = request.getParameter("searchContent");
		}
        
        System.out.println(keyword);
        System.out.println(searchContent);
        // 페이지 관련 정보
        PageDTO pdto = new PageDTO(pageNum, keyword, searchContent);
        model.addAttribute("pageInfo", pdto);
        
        // 페이징 처리된 리스트(쿼리에서 쓰임/ 현재 페이지 넘버와 키워드 보내줌)
        List<EmpListVO> list = wonwooEduService.getPagingList(pdto);
        model.addAttribute("list", list);
        
        // 페이징 처리된 숫자그룹(뷰에서)
        int total = wonwooEduService.totalCount(pdto);
        PaginationDTO pageDto = new PaginationDTO(total, pdto);
     
        model.addAttribute("paging", pageDto);
        model.addAttribute("title", "교육 인원할당");

        return "/admin/edu/allocationCheckbox";
	}
	
	@ResponseBody
	@PostMapping("/allocation2")
	public void eduAllocCheck(@RequestBody Map<String, Object> param) {
		System.out.println("empNo"+param.get("empNo"));
		System.out.println("eduNo"+param.get("eduNo"));
		service.eduHistoryAdd(param);
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
	
	@GetMapping("/ajax")
	public String ajaxTest() {
		return "admin/edu/ajaxTest";
	}
}