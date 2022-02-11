package com.metanet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.metanet.domain.EmployeeVO;
import com.metanet.persistence.EmployeeMapper;
import com.metanet.persistence.EmployeeRepository;

@Service
public class EmployeeServiceImpl {
	
	@Autowired
	EmployeeMapper empMapper;
	
	@Autowired
	EmployeeRepository empRepo;
	
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
	
	// 사원의 인사이동(UPDATE)
	public int updateEmpDeptAndPos(int empNo, int deptNo, int posNo){
		EmployeeVO emp = empRepo.findByEmpNo(empNo);
		emp.getDept().setDeptNo(deptNo);
		emp.getPos().setPosNo(posNo);
		return empMapper.updateEmp(emp);
	}
	// 사원의 부서이동(UPDATE)
	public int updateEmpDept(int empNo, int deptNo)	{
		EmployeeVO emp = empRepo.findByEmpNo(empNo);
		emp.getDept().setDeptNo(deptNo);
		return empMapper.updateEmp(emp);
	}
	
	// 사원의 직급이동(UPDATE)
	public int updateEmpPos(int empNo, int posNo)	{
		EmployeeVO emp = empRepo.findByEmpNo(empNo);
		emp.getPos().setPosNo(posNo);
		return empMapper.updateEmp(emp);
	}
}
