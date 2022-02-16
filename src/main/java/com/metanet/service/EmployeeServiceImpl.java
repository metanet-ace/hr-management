package com.metanet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.metanet.domain.DepartmentVO;
import com.metanet.domain.EmpHistoryVO;
import com.metanet.domain.EmpWorkingtimeVO;
import com.metanet.domain.EmployeeVO;
import com.metanet.domain.PositionVO;
import com.metanet.persistence.DepartmentRepository;
import com.metanet.persistence.EmpWorkingtimeRepository;
import com.metanet.persistence.EmployeeHistoryRepository;
import com.metanet.persistence.EmployeeMapper;
import com.metanet.persistence.EmployeeRepository;
import com.metanet.persistence.PositionRepository;
import com.metanet.persistence.QuerydslRepository;

@Service
public class EmployeeServiceImpl {
	
	@Autowired
	EmployeeMapper empMapper;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired 
	EmployeeHistoryRepository empHisRepo;
	
	@Autowired 
	DepartmentRepository deptRepo;
	
	@Autowired
	PositionRepository posRepo;
	
	@Autowired
	EmpWorkingtimeRepository workRepo;
	
	@Autowired
	QuerydslRepository qdslRepo;
	
	// 세션에 로그인 된 사원 정보 넣어주기
	public EmployeeVO getLoginedEmp(int empNo) {
		return empRepo.findByEmpNo(empNo);
	}
	
	// 페이징 처리된 사원 전체 리스트 
	public Page<EmployeeVO> getEmpList(Pageable pageable){
		return empRepo.findAll(pageable);
	}
	
	// 페이징 처리되고 부서 검색으로 출력된 전체 리스트
	public Page<EmployeeVO> getEmpListWithDept(String deptName, Pageable pageable){
		return empRepo.findByEmpWithDeptName(deptName, pageable);
	}
	
	// 페이징 처리되고 직급 검색으로 출력된 전체 리스트
	public Page<EmployeeVO> getEmpListWithPos(String posName, Pageable pageable){
		return empRepo.findByEmpWithPosName(posName, pageable);
	}
	
	// 페이징 처리되고 이름 검색으로 출력된 사원 리스트
	public Page<EmployeeVO> getEmpListWithName(String empName, Pageable pageable){
		return empRepo.findByEmpNameContaining(empName, pageable);
	}

	// 부서 전체 출력 
	public List<DepartmentVO> getDeptList(){
		return deptRepo.findAll();
	}
	
	// 직급 전체 출력 
	public List<PositionVO> getPosList(){
		return posRepo.findAll();
	}
	// 사원의 부서, 직급이동(UPDATE)
	public int updateEmpDeptAndPos(int empNo, int deptNo, int posNo, String reason){
		EmployeeVO emp = empRepo.findByEmpNo(empNo);

		// 사원의 기존 부서, 직급 정보 저장 
		EmpHistoryVO empHis = new EmpHistoryVO();
		empHis.setBeforeDept(emp.getDept().getDeptName()); 
		empHis.setBeforePos(emp.getPos().getPosName());
		
		// 부서, 직급 변경 
		emp.setBatisDeptNo(deptNo);
		emp.setBatisPosNo(posNo);
		empMapper.updateEmp(emp);
		
		// 히스토리 테이블 정보 입력
		empHis.setBatisEmpNo(empNo);
		empHis.setDeptNo(deptNo);
		empHis.setPosNo(posNo);
		empHis.setIssuedDate(new Date());
		empHis.setIssuedOrder("인사이동");
		empHis.setIssuedContent(reason);
		
		return empMapper.saveHistory(empHis); 
	}
	// 사원의 부서이동(UPDATE)
	public int updateEmpDept(int empNo, int deptNo, String reason)	{
		EmployeeVO emp = empRepo.findByEmpNo(empNo);
		System.out.println("+++++++++++++++++++++++++++" + emp);
		// 사원의 기존 부서, 직급 정보 저장 
		EmpHistoryVO empHis = new EmpHistoryVO();
		empHis.setBeforeDept(emp.getDept().getDeptName());
		empHis.setBeforePos(emp.getPos().getPosName());
		empHis.setPosNo(emp.getBatisPosNo());
		
		// 부서 변경
		emp.setBatisDeptNo(deptNo);
		emp.setBatisPosNo(emp.getPos().getPosNo());
		empMapper.updateEmp(emp);
		
		// 히스토리 테이블 정보 입력
		empHis.setBatisEmpNo(empNo);
		empHis.setDeptNo(deptNo);
		empHis.setIssuedDate(new Date());
		empHis.setIssuedOrder("부서이동");
		empHis.setIssuedContent(reason);
	
		return empMapper.saveHistory(empHis); 
	}
	
	// 사원의 직급이동(UPDATE)
	public int updateEmpPos(int empNo, int posNo, String reason)	{
		EmployeeVO emp = empRepo.findByEmpNo(empNo);
		// 사원의 기존 부서, 직급 정보 저장 
		EmpHistoryVO empHis = new EmpHistoryVO();
		empHis.setBeforeDept(emp.getDept().getDeptName());
		empHis.setBeforePos(emp.getPos().getPosName());
		empHis.setDeptNo(emp.getBatisDeptNo());
		
		// 직급 변경
		emp.setBatisPosNo(posNo);
		emp.setBatisDeptNo(emp.getDept().getDeptNo());
		empMapper.updateEmp(emp);
		
		// 히스토리 테이블 정보 입력
		empHis.setBatisEmpNo(empNo);
		empHis.setPosNo(posNo);
		empHis.setIssuedDate(new Date());
		empHis.setIssuedOrder("직급변경");
		empHis.setIssuedContent(reason);
		
		return empMapper.saveHistory(empHis); 
	}	
	
	// 퇴사자 처리
	public void updateRetire(int empNo, String retireReason) {
		EmployeeVO emp = empRepo.findByEmpNo(empNo);
		emp.setEmpRetdate(new Date());
		EmployeeVO updatedEmp = empRepo.save(emp);
		
		EmpHistoryVO empHis = new EmpHistoryVO();
		// 영속성 컨텍스트가 아닌 자바 객체 상에서 연관 관계를 사용하려면 setter로 값을 주입시켜주어야 한다.
		empHis.setEmp(updatedEmp);
		empHis.getEmp().setEmpNo(empNo);
		empHis.setBeforeDept("-");
		empHis.setBeforePos("-");
		empHis.setIssuedContent(retireReason);
		empHis.setIssuedDate(new Date());
		empHis.setIssuedOrder("퇴사");
		
		empHisRepo.save(empHis);
	}
	
	// 페이징 + 전체) 히스토리 리스트
	public Page<EmpHistoryVO> getAllEmpHistory(Pageable pageable){
		return empHisRepo.findAll(pageable);
	}
	// 페이징 + 조건) 히스토리 리스트
	public Page<EmpHistoryVO> getEmpHistoryList(HashMap<String, String> data, Pageable pageable){
		
		if(data.get("empName") != null) {
			return empHisRepo.findByEmpEmpNameContaining(data.get("empName"), pageable);
		} else if (data.get("startDate") != null && data.get("endDate") != null) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				Date startDate = sdf.parse(data.get("startDate"));
				Date endDate = sdf.parse(data.get("endDate"));
				// 종료일은 1일을 더해주어야 한다.
				endDate.setDate(endDate.getDate()+1);
				return empHisRepo.findByIssuedDateBetween(startDate, endDate, pageable);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return empHisRepo.findAll(pageable);
	}
	
	// 출근 시간 등록 
	public void insertStartTime(int empNo) {
		EmployeeVO emp = empRepo.findByEmpNo(empNo);

		EmpWorkingtimeVO workTimeVO = new EmpWorkingtimeVO();
		workTimeVO.setEmp(emp);
		workTimeVO.setWorkStart(new Date());
		workTimeVO.setWorkType(null);
		
		workRepo.save(workTimeVO);
	}
	
	// 근무 시간 뷰어
	public List<Map<String, Object>> selectWorkingTime(int empNo){
		
		Map<String, String> param = new HashMap<String, String>();
		String strEmpNo = Integer.toString(empNo);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		System.out.println(dateFormat.format(cal.getTime()));
		
		System.out.println("해당년도: " + cal.get(Calendar.YEAR));
		System.out.println("해당월: " + cal.get(Calendar.MONTH) + 1); // MONTH는 0부터 시작
		System.out.println("첫번째 일: " + cal.getMinimum(Calendar.DAY_OF_MONTH));
		
		System.out.println("마지막 일(현재 날짜 기준 최대수)" + cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		System.out.println("마지막 일(Calendar이 가진 최대수)" + cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		param.put("empNo", strEmpNo);
		param.put("startdate", "20220201");
		param.put("enddate", "20220228");
		
		return empMapper.findWorkingDate(param);
	}
}
