package com.metanet.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.metanet.domain.DepartmentVO;
import com.metanet.domain.EmpHistoryVO;
import com.metanet.domain.EmpWorkingtimeVO;
import com.metanet.domain.EmployeeVO;
import com.metanet.domain.PositionVO;
import com.metanet.exception.LoginException;
import com.metanet.service.EmployeeServiceImpl;

@SessionAttributes("sessionEmp")
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl empService;
	
	@Autowired
	suinEduController batchCtrl;
	
	// 인사이동 페이징(VIEW) 컨트롤러
	@GetMapping("/admin/emp")
	public String userList(Model model, @PageableDefault(size = 10, sort = "empNo",
				direction = Sort.Direction.DESC ) Pageable pageable,
			@RequestParam(required = false, defaultValue = "") String field, 
			@RequestParam(required = false, defaultValue = "") String word) {
		// 검색 조건 없을 시 
		Page<EmployeeVO> emp = null;
		
		// 필드명에 따른 로직 처리	
		if(field.equals("deptName")) {
			emp = empService.getEmpListWithDept(word, pageable);
			model.addAttribute("field", "deptName");
		} else if (field.equals("posName")){
			emp = empService.getEmpListWithPos(word, pageable);
			model.addAttribute("field", "posName");
		} else if (field.equals("empName")) {
			emp = empService.getEmpListWithName(word, pageable);
			model.addAttribute("field", "empName");
		} else {
			emp = empService.getEmpList(pageable);
		}
		int totalPages = emp.getTotalPages(); // 총 페이지 개수 
		int pageNumber = emp.getPageable().getPageNumber(); // 현재 페이지
		int pageBlock = 5; // 블럭의 수
		
		int startBlockPage = (pageNumber/pageBlock)*pageBlock+1;
		int endBlockPage = startBlockPage + pageBlock-1;
		endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;
		
		// 부서 전체 이름 출력
		List<DepartmentVO> deptList = empService.getDeptList();
		// 직급 전체 이름 출력
		List<PositionVO> posList = empService.getPosList();
		
		model.addAttribute("deptList", deptList);
		model.addAttribute("posList", posList);
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("emplist", emp);
		model.addAttribute("title", "사원 전체 리스트");
		
		return "/admin/empList";
	}
	
	// 인사 이동 (부서변경, 직급변경) 컨트롤러	
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
	
	// 인사 이동 히스토리 페이징 컨트롤러 
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
	
	// 출근 시간 등록 컨트롤러 
	@PostMapping("/emp/recordTime")
	@ResponseBody
	public ResponseEntity<EmpWorkingtimeVO> workingTimeRecoder(@RequestBody HashMap<String, String> data){
		
		int empNo = Integer.parseInt(data.get("empNo"));
		
		if(empService.findStartWorkingTime(empNo) != null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		EmpWorkingtimeVO result = empService.insertStartTime(empNo);
		
		// 날짜 포맷 변경 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result.setFormattedDate(sdf.format(result.getWorkStart()));
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// 출근 시간 리드 컨트롤러
	@PostMapping("/emp/workingTimeList")
	@ResponseBody
	public ResponseEntity<List<Map<String, Object>>> workingTimeViewer(@RequestBody Map<String, String> map){
		Integer empNo = Integer.parseInt(map.get("empNo"));
		
		List<Map<String, Object>> result = empService.selectWorkingTime(empNo, map);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// 퇴근 시간 등록 컨트롤러 
	@PostMapping("/emp/recordEndTime")
	@ResponseBody
	public ResponseEntity<EmpWorkingtimeVO> workingEndTimeRecoder(@RequestBody HashMap<String, String> data) throws ParseException{
		int empNo = Integer.parseInt(data.get("empNo"));
		String notParsedstartTime = data.get("startTime");
		
		// 출근 버튼을 안눌렀으면
		if(empService.findStartWorkingTime(empNo) == null) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		// 이미 등록된 퇴근 시간이 있는지 확인
		if(empService.findEndWorkingTime(empNo) != null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		// 어제까지한 근무시간 확인 ( 초 단위로 결과를 출력했음 )
		int totalTime = empService.findTotalTime(empNo);
		
		// 이미 52시간을 초과한 상태이면 오류 발생
		if(totalTime >=  52 * 60 * 60) {
			return new ResponseEntity<>(null, HttpStatus.LOCKED);
		}
		
		// 오늘 근무시간 확인 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Calendar endTime = Calendar.getInstance();
		endTime.setTime(new Date());
		
		Calendar startTime = Calendar.getInstance();
		Date parsedStartTime = sdf.parse(notParsedstartTime);
		startTime.setTime(parsedStartTime);
		
		// 출근시간과 퇴근시간의 차이를 구함(초단위)
		long today = (endTime.getTimeInMillis() - startTime.getTimeInMillis()) / 1000;
		// 체크할 시간 (초단위)
		long check = totalTime + today;
		System.out.println(check + "check");
		
		// 총 근무시간 체킹
		if(check > 52 * 60 * 60) { // 주 52시간(초단위) 초과했다면 52시간까지 인정되는 시간까지만 DB에 저장된다
			// 52시간 - 어제까지 일한 시간 = 남은 잉여 시간 (초단위)
			long restTime = (52 * 60 * 60) - totalTime;
			// 오늘 일한 시간(밀리초) + 잉여 시간(초 * 1000)
			long maxTime = startTime.getTimeInMillis() + (restTime*1000);
			// 변환
			Date finalTime = new Date(maxTime);
			EmpWorkingtimeVO result = empService.insertEndTime(empNo, finalTime);
			
			int hour, min, sec;
			int recountTotalTime = empService.findTotalTime(empNo);
			hour = recountTotalTime / 3600;
			min = recountTotalTime % 3600 / 60;
			sec = recountTotalTime % 3600 % 60;
			
			String totalTimeStr = hour + "시간 " + min + "분 " + sec + "초 "; 
			
			// 날짜 포맷 변경 (변경 되자마자 AJAX로 보여줄 때)
			result.setFormattedDate(sdf.format(result.getWorkEnd()));
			result.setTotalTime(totalTimeStr);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
		
		// 퇴근 시간 INSERT
		EmpWorkingtimeVO result = empService.insertEndTime(empNo);
		
		// 하루 근무 시간 계산
		totalTime = empService.findTotalTime(empNo);

		int hour, min, sec;
		
		hour = totalTime / 3600;
		min = totalTime % 3600 / 60;
		sec = totalTime % 3600 % 60;
		
		String totalTimeStr = hour + "시간 " + min + "분 " + sec + "초 "; 
		
		// 날짜 포맷 변경 (변경 되자마자 AJAX로 보여줄 때)
		result.setFormattedDate(sdf.format(result.getWorkEnd()));
		result.setTotalTime(totalTimeStr);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	// 시큐리티 사용한 로그인 화면
	@GetMapping("/signin")
	public String loginView() {
		return "loginWithSecurity";
	}

	// 시큐리티 사용한 로그인 성공시 이동하는 화면
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
	
	// 메인 페이지 
	@GetMapping("/main")
	public String mainView(Model model, @SessionAttribute("sessionEmp") EmployeeVO emp ) {
		
		// 출근 시간 정보 가져오기 
		EmpWorkingtimeVO workVo = empService.findStartWorkingTime(emp.getEmpNo());
		// 퇴근 시간 정보 가져오기
		EmpWorkingtimeVO workVo2 = empService.findEndWorkingTime(emp.getEmpNo());
		// 누적 근무 시간 출력
		int totalTime = empService.findTotalTime(emp.getEmpNo());
		int hour, min, sec;
		hour = totalTime / 3600;
		min = totalTime % 3600 / 60;
		sec = totalTime % 3600 % 60;
		String totalTimeStr = hour + "시간 " + min + "분 " + sec + "초 "; 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   
	    String startTime = null;
	    String endTime = null;
	    
	    if(workVo == null) {
	    	// 출근을 아직 안눌렀다면 
	    	model.addAttribute("totalTime", totalTimeStr);
	    	return "index";
	    } 
	    
	    if(workVo2 == null) {
	    	// 출근을 하고 퇴근을 아직 안했다면 
	    	startTime = sdf.format(workVo.getWorkStart());
	    	model.addAttribute("startTime", startTime);
	    	model.addAttribute("totalTime", totalTimeStr);
	    	return "index";
	    }
	    
	    // 출근도 하고 퇴근도 찍었다면
		startTime = sdf.format(workVo.getWorkStart());
	    endTime = sdf.format(workVo2.getWorkEnd());
    	model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("totalTime", totalTimeStr);
		
		return "index";
	}
	
	// 엑세스 접근 권한이 없습니다 페이지로 이동
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}
	
}
