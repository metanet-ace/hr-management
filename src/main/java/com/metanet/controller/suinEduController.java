package com.metanet.controller;

import java.text.ParseException;
import java.util.ArrayList;
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
		service.eduProgress();
	}
	
	@RequestMapping("/allocation2/{eduNo}")
	public String eduAllocation(@PathVariable("eduNo") int eduNo, 
								Model model, HttpServletRequest request) {	
		int pageNum=1;
		String keyField = "";
		String keyword = "";
		/* int edu_no = Integer.parseInt(request.getParameter("edu_no")); */
		
		
		System.out.println(request.getParameter("pageNum"));
		
		 // 현재 페이지 넘버 
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt((request.getParameter("pageNum")).trim());
		}
		model.addAttribute("tempPageNum", pageNum);
        
        // 키워드(작성자/작성내용 등)
        if(request.getParameter("keyField") != null && request.getParameter("keyField") != ""){
        	keyField = request.getParameter("keyField");	
		}
        
        if(request.getParameter("keyword") != null && request.getParameter("keyword") != ""){
        	keyword = request.getParameter("keyword");
		}
        
        System.out.println(keyField);
        System.out.println(keyword);
        // 페이지 관련 정보
        PageDTO pdto = new PageDTO(pageNum, keyField, keyword);
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
	
//	@GetMapping("/admin/history")
//	public String eduHistoryList(Model model) {
//		model.addAttribute("eduHistoryList", service.getEduHistoryList());
//		return "/admin/edu/history";
//	}
	
	@RequestMapping(value={"/admin/history", "/admin/history/{pageNum}", "/admin/history/{pageNum}/{keyField}/{keyword}"})
	public String eduHistoryListByKeyword(@PathVariable(value="pageNum", required=false) Integer p, 
										  @PathVariable(value="keyField", required=false) String keyField,
										  @PathVariable(value="keyword", required=false) String keyword,
										  Model model, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		System.out.println("keyField: " + keyField +" keyword: " + keyword);
		
		if(request.getParameter("keyField") != null) {
			keyField = request.getParameter("keyField");
			keyword = request.getParameter("keyword");
		}
		
		int pageNum=1;

		// 현재 페이지 넘버
		if (p != null) {
			pageNum = p;
		}
		model.addAttribute("tempPageNum", pageNum);

		System.out.println(keyField);
		System.out.println(keyword);
		// 페이지 관련 정보
		PageDTO pdto = new PageDTO(pageNum, keyField, keyword);
		model.addAttribute("pageInfo", pdto);

		// 페이징 처리된 리스트(쿼리에서 쓰임/ 현재 페이지 넘버와 키워드 보내줌)
		//model.addAttribute("list", service.getEduHistoryList(pdto));
		
		map.put("keyField", keyField);
		map.put("keyword", keyword);

		// 페이징 처리된 숫자그룹(뷰에서)
		int total = service.eduHistoryTotalCount(map);
		PaginationDTO pageDto = new PaginationDTO(total, pdto);

		model.addAttribute("paging", pageDto);
		
		map.put("keyField", keyField);
		map.put("keyword", keyword);
		model.addAttribute("eduHistoryList", service.getEduHistoryList(pdto));
		model.addAttribute("keyField", keyField);
		model.addAttribute("keyword", keyword);
		return "/admin/edu/history";
	}
	
	@ResponseBody
	@PostMapping("/score")
	public void eduScore(@RequestBody Map<String, String[]> map) {
		System.out.println(map.get("score"));
		System.out.println(map.get("eduHisno"));
		System.out.println("map.isEmpty(): "+map.isEmpty());
		List<EduHistoryVO> list = new ArrayList<EduHistoryVO>();
		for(int i = 0; i < map.get("score").length; i++) {
			EduHistoryVO vo = new EduHistoryVO();
			vo.setEduHisno(Integer.parseInt(map.get("eduHisno")[i]));
			vo.setScore(map.get("score")[i]);
			list.add(vo);
		}
		System.out.println(list);
		service.eduScoreUpdate(list);
	}
}