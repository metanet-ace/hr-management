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
	
	@Scheduled(cron="0 0 0 * * ?", zone="Asia/Seoul")
	public void schedulerTest() throws ParseException {
		System.out.println("Scheduler Test..." + new Date());
		service.eduAttendance();
		service.eduProgress();
	}
	
	@RequestMapping(value={"/list", "/list/{pageNum}", "/list/{pageNum}/{keyField}/{keyword}"})
	public String eduList(@PathVariable(value = "pageNum", required = false) Integer p,
						  @PathVariable(value = "keyField", required = false) String keyField,
						  @PathVariable(value = "keyword", required = false) String keyword,
						  Model model, HttpServletRequest request) {
		int pageNum = 1;

		// 현재 페이지 넘버
		if (p != null) {
			pageNum = p;
		}
		
		if(request.getParameter("keyField") != null) {
			keyField = request.getParameter("keyField");
			keyword = request.getParameter("keyword");
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyField", keyField);
		map.put("keyword", keyword);

		model.addAttribute("tempPageNum", pageNum);

        // 페이지 관련 정보
        PageDTO pdto = new PageDTO(pageNum, keyField, keyword);
        model.addAttribute("pageInfo", pdto);
        
		model.addAttribute("list", service.eduListSelect(pdto));

		// 페이징 처리된 숫자그룹(뷰에서)
		int total = service.getTotalCountEduList(map);
		PaginationDTO pageDto = new PaginationDTO(total, pdto);

		model.addAttribute("paging", pageDto);
		model.addAttribute("title", "교육 전체리스트");
		model.addAttribute("keyField", keyField);
		model.addAttribute("keyword", keyword);
		return "admin/edu/list";
	}
	
	@RequestMapping(value={"/allocation/{eduNo}", "/allocation/{eduNo}/{pageNum}", "/allocation/{eduNo}/{pageNum}/{keyField}/{keyword}"})
	public String eduAllocation(@PathVariable("eduNo") int eduNo,
								@PathVariable(value = "pageNum", required = false) Integer p,
								@PathVariable(value = "keyField", required = false) String keyField,
								@PathVariable(value = "keyword", required = false) String keyword,
								Model model, HttpServletRequest request) {	
		int pageNum=1;

		// 현재 페이지 넘버
		if (p != null) {
			pageNum = p;
		}
		
		if(request.getParameter("keyField") != null) {
			keyField = request.getParameter("keyField");
			keyword = request.getParameter("keyword");
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyField", keyField);
		map.put("keyword", keyword);

		model.addAttribute("tempPageNum", pageNum);

        // 페이지 관련 정보
        PageDTO pdto = new PageDTO(pageNum, keyField, keyword);
        model.addAttribute("pageInfo", pdto);
        
        // 페이징 처리된 리스트(쿼리에서 쓰임/ 현재 페이지 넘버와 키워드 보내줌)
        model.addAttribute("list", service.empListForAllocationSelect(pdto));
        
        // 페이징 처리된 숫자그룹(뷰에서)
        int total = service.getTotalCountForAllocation(map);
        PaginationDTO pageDto = new PaginationDTO(total, pdto);
     
        model.addAttribute("paging", pageDto);
        model.addAttribute("title", "교육 배정");
        model.addAttribute("keyField", keyField);
		model.addAttribute("keyword", keyword);
        return "/admin/edu/allocation";
	}
	
	@ResponseBody
	@PostMapping("/allocation")
	public void eduAllocCheck(@RequestBody Map<String, Object> param) {
		System.out.println("empNo"+param.get("empNo"));
		System.out.println("eduNo"+param.get("eduNo"));
		service.eduHistoryAdd(param);
	}
	
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
		model.addAttribute("eduHistoryList", service.getEduHistoryList(pdto));
		model.addAttribute("keyField", keyField);
		model.addAttribute("keyword", keyword);
		model.addAttribute("title","교육 이수사항 조회");
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