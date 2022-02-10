package com.metanet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	// 페이징 처리된 사원 전체 리스트 
	public Page<EmployeeVO> getEmpList(Pageable pageable){
		return empRepo.findAll(pageable);
	}
	
	
	
}
