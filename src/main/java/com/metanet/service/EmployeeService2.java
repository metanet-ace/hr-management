package com.metanet.service;

import com.metanet.domain.EmployeeVO2;

public interface EmployeeService2 {
	
	public EmployeeVO2 selectLogin(EmployeeVO2 emp); //로그인(공통)
	public int insertEmp(EmployeeVO2 emp); // 사원등록(인사팀)
}
