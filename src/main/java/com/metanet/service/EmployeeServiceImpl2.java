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
		encoder.encode(emp.getEmpPwd());
		return empMapper.insertEmp(emp);
	}
	
}
