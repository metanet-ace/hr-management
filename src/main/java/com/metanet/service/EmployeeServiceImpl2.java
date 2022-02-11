package com.metanet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.metanet.domain.EmployeeVO2;
import com.metanet.persistence.EmployeeMapper2;

@Service
public class EmployeeServiceImpl2 implements EmployeeService2{
	
	@Autowired
	EmployeeMapper2 empMapper;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	//사원등록(인사팀)
	public int insertEmp(EmployeeVO2 emp) {
		emp.setEmpPwd(encoder.encode(emp.getEmpPwd()));
		return empMapper.insertEmp(emp);
	}
	
	@Override
	//사원 상세정보
	public EmployeeVO2 selectOne(int empNo) {
		return empMapper.selectOne(empNo);
	}
	
	@Override
	// 사원수정하기(인사팀) : 비밀번호수정x
	public int updateEmp(EmployeeVO2 emp) {
		System.out.println("service에서 empNo확인" + emp.getEmpNo());
		return empMapper.updateEmp(emp);
	}
}
