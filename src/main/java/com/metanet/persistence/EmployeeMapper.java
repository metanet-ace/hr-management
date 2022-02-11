package com.metanet.persistence;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.EmployeeVO;

@Mapper
public interface EmployeeMapper {
	// 사원 부서, 직급 업데이트 
	public int updateEmp(EmployeeVO emp);
}
