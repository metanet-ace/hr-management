package com.metanet.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.metanet.domain.EmployeeVO;

public interface EmployeeRepository extends CrudRepository<EmployeeVO, Integer>{
	List<EmployeeVO> findByEmpName(String name);
	EmployeeVO findByEmpNo(int username);
	EmployeeVO findByEmpNoAndEmpPwd(int username, String password);
}
