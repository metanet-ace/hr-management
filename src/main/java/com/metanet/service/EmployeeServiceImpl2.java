package com.metanet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metanet.domain.EmployeeVO2;
import com.metanet.persistence.EmployeeMapper2;

@Service
public class EmployeeServiceImpl2 implements EmployeeService2{
	
	@Autowired
	EmployeeMapper2 empMapper;
	
	@Override
	//로그인(공통)
	public EmployeeVO2 selectLogin(EmployeeVO2 emp) {
		System.out.println("===================" + emp);
		System.out.println("매퍼" + empMapper.selectLogin(emp));
		return empMapper.selectLogin(emp);
	}
	
	@Override
	//사원등록(인사팀)
	public int insertEmp(EmployeeVO2 emp) {
		return empMapper.insertEmp(emp);
	}
	
}
