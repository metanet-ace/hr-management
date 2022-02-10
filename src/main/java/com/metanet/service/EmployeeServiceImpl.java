package com.metanet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public EmployeeVO login(int username, String password) {
		return empRepo.findByEmpNoAndEmpPwd(username, password);
	}
	
	public EmployeeVO selectAll() {
		return (EmployeeVO) empRepo.findAll();
	}
	
	public List<EmployeeVO> getEmpList(){
		return empMapper.getEmpList();
	}
	
}
