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
import org.springframework.web.bind.annotation.SessionAttribute;

import com.metanet.domain.EduHistoryVO;
import com.metanet.domain.EduVO;
import com.metanet.domain.EmployeeVO;
import com.metanet.domain.LogVO;
import com.metanet.domain.PageDTO;
import com.metanet.domain.PaginationDTO;
import com.metanet.persistence.Util;
import com.metanet.service.WonwooEduService;
import com.metanet.service.suinEduService;


@Controller
@RequestMapping("/edu")
@Component
public class suinEduController {
	@Autowired
	private suinEduService service;
	
	@Autowired
	private WonwooEduService logService;
	
	@Scheduled(cron="0 0 0 * * ?", zone="Asia/Seoul")
	public void schedulerTest() throws ParseException {
		service.eduAttendance();
		service.eduProgress();
	}
	
	//등록된 교육 과정 목록
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
		model.addAttribute("title", "교육 목록");
		model.addAttribute("keyField", keyField);
		model.addAttribute("keyword", keyword);
		return "admin/edu/list";
	}
	
	//교육 인원 배정 페이지 출력 관련
	@RequestMapping(value={"/allocation/{eduNo}/{eduTitle}", "/allocation/{eduNo}/{eduTitle}/{pageNum}", "/allocation/{eduNo}/{eduTitle}/{pageNum}/{keyField}/{keyword}"})
	public String eduAllocation(@PathVariable("eduNo") int eduNo,
								@PathVariable("eduTitle") String eduTitle,
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
	
	//교육 인원 배정
	@ResponseBody
	@PostMapping("/allocation")
	public void eduAllocCheck(@RequestBody Map<String, Object> param, HttpServletRequest request, 
			LogVO log, @SessionAttribute("sessionEmp") EmployeeVO emp) {
		log.setLogIp(Util.getUserIp(request));
		log.setEmpNo(emp.getEmpNo());
		log.setLogTarget("교육인원 할당");
		log.setLogDesc("교육번호: " +param.get("eduNo") +" 배정된 사원: "+param.get("empNo"));
		service.eduHistoryAdd(param);
		logService.writeLog(log);
	}
	
	@RequestMapping(value={"/admin/history", "/admin/history/{pageNum}", "/admin/history/{pageNum}/{keyField}/{keyword}"})
	public String eduHistoryListByKeyword(@PathVariable(value="pageNum", required=false) Integer p, 
										  @PathVariable(value="keyField", required=false) String keyField,
										  @PathVariable(value="keyword", required=false) String keyword,
										  Model model, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		
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

		// 페이지 관련 정보
		PageDTO pdto = new PageDTO(pageNum, keyField, keyword);
		model.addAttribute("pageInfo", pdto);
		
		map.put("keyField", keyField);
		map.put("keyword", keyword);

		// 페이징 처리된 숫자그룹(뷰에서)
		int total = service.eduHistoryTotalCount(map);
		PaginationDTO pageDto = new PaginationDTO(total, pdto);

		model.addAttribute("paging", pageDto);
		model.addAttribute("eduHistoryList", service.getEduHistoryList(pdto));
		model.addAttribute("keyField", keyField);
		model.addAttribute("keyword", keyword);
		model.addAttribute("title","교육 진행 관리");
		return "/admin/edu/history";
	}
	
	//교육 점수 등록
	@ResponseBody
	@PostMapping("/score")
	public void eduScore(@RequestBody Map<String, String[]> map, HttpServletRequest request, 
			LogVO log, @SessionAttribute("sessionEmp") EmployeeVO emp) {
		
		List<EduHistoryVO> list = new ArrayList<EduHistoryVO>();
		Map<String, String> logData = new HashMap<String, String>();
		for(int i = 0; i < map.get("score").length; i++) {
			EduHistoryVO vo = new EduHistoryVO();
			vo.setEduHisno(Integer.parseInt(map.get("eduHisno")[i]));
			vo.setScore(map.get("score")[i]);
			list.add(vo);
			logData.put("교육 등록 번호: "+map.get("eduHisno")[i], "점수: "+map.get("score")[i]);
		}
		log.setLogIp(Util.getUserIp(request));
		log.setEmpNo(emp.getEmpNo());
		log.setLogTarget("교육 점수 입력");
		log.setLogDesc(""+logData);
		logService.writeLog(log);
		service.eduScoreUpdate(list);
		
	}
	
	//교육 과정 달력
	@GetMapping("/calendar")
	public String eduCalendar(Model model) {
		model.addAttribute("title", "교육 일정 달력");
		model.addAttribute("calList", service.eduCalendarList());
		return "/admin/edu/calendar";
	}
	
}