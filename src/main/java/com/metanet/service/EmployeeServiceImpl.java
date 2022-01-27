package com.metanet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metanet.domain.EmployeeVO;
import com.metanet.persistence.EmployeeMapper;

@Service
public class EmployeeServiceImpl {
	
	@Autowired
	EmployeeMapper empMapper;
	
	public List<EmployeeVO> getEmpList(){
		return empMapper.getEmpList();
	}
	
}
