package com.metanet.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.metanet.domain.EmpHistoryVO;
import com.metanet.domain.EmployeeVO;
import com.metanet.persistence.EmployeeHistoryRepository;
import com.metanet.persistence.EmployeeMapper;
import com.metanet.persistence.EmployeeRepository;

@Service
public class EmployeeServiceImpl {
	
	@Autowired
	EmployeeMapper empMapper;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired 
	EmployeeHistoryRepository empHisRepo;
	
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
	
	// 사원의 부서, 직급이동(UPDATE)
	public int updateEmpDeptAndPos(int empNo, int deptNo, int posNo, String reason){
		EmployeeVO emp = empMapper.findByEmpNo(empNo);

		// 사원의 기존 부서, 직급 정보 저장 
		EmpHistoryVO empHis = new EmpHistoryVO();
		empHis.setBeforeDept(emp.getBatisDeptNo()); 
		empHis.setBeforePos(emp.getBatisPosNo());
		
		// 부서, 직급 변경 
		emp.setBatisDeptNo(deptNo);
		emp.setBatisPosNo(posNo);
		empMapper.updateEmp(emp);
		
		// 히스토리 테이블 정보 입력
		empHis.setEmpNo(empNo);
		empHis.setDeptNo(deptNo);
		empHis.setPosNo(posNo);
		empHis.setIssuedDate(new Date());
		empHis.setIssuedOrder("부서이동");
		empHis.setIssuedContent(reason);
		
		return empMapper.saveHistory(empHis); 
	}
	// 사원의 부서이동(UPDATE)
	public int updateEmpDept(int empNo, int deptNo, String reason)	{
		EmployeeVO emp = empMapper.findByEmpNo(empNo);
		// 사원의 기존 부서, 직급 정보 저장 
		EmpHistoryVO empHis = new EmpHistoryVO();
		empHis.setBeforeDept(emp.getBatisDeptNo());
		empHis.setBeforePos(emp.getBatisPosNo());
		empHis.setPosNo(emp.getBatisPosNo());
		
		// 부서 변경
		emp.setBatisDeptNo(deptNo);
		empMapper.updateEmp(emp);
		
		// 히스토리 테이블 정보 입력
		empHis.setEmpNo(empNo);
		empHis.setDeptNo(deptNo);
		empHis.setIssuedDate(new Date());
		empHis.setIssuedOrder("부서이동");
		empHis.setIssuedContent(reason);
	
		return empMapper.saveHistory(empHis); 
	}
	
	// 사원의 직급이동(UPDATE)
	public int updateEmpPos(int empNo, int posNo, String reason)	{
		EmployeeVO emp = empMapper.findByEmpNo(empNo);
		// 사원의 기존 부서, 직급 정보 저장 
		EmpHistoryVO empHis = new EmpHistoryVO();
		empHis.setBeforeDept(emp.getBatisDeptNo());
		empHis.setBeforePos(emp.getBatisPosNo());
		empHis.setDeptNo(emp.getBatisDeptNo());
		
		// 직급 변경
		emp.setBatisPosNo(posNo);
		empMapper.updateEmp(emp);
		
		// 히스토리 테이블 정보 입력
		empHis.setEmpNo(empNo);
		empHis.setPosNo(posNo);
		empHis.setIssuedDate(new Date());
		empHis.setIssuedOrder("부서이동");
		empHis.setIssuedContent(reason);
		
		return empMapper.saveHistory(empHis); 
	}	
}
