package com.metanet.persistence;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.EmployeeVO2;

@Mapper
public interface EmployeeMapper2 {
	public int insertEmp(EmployeeVO2 emp);	//사원등록(인사팀)
}
