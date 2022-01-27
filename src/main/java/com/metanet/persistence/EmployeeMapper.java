package com.metanet.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.EmployeeVO;

@Mapper
public interface EmployeeMapper {
	
	public List<EmployeeVO> getEmpList();
}
