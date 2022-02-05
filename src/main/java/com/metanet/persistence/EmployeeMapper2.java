package com.metanet.persistence;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.EmployeeVO2;

@Mapper
public interface EmployeeMapper2 {
	public EmployeeVO2 selectLogin(EmployeeVO2 emp);	//로그인(공통)
	public int insertEmp(EmployeeVO2 emp);	//사원등록(인사팀)
}
