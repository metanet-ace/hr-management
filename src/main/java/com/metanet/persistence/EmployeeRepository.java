package com.metanet.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.metanet.domain.EmployeeVO;

public interface EmployeeRepository extends CrudRepository<EmployeeVO, Integer>{
	List<EmployeeVO> findByEmpName(String name);
	EmployeeVO findByEmpNo(int username);
	
	// 시큐리티 로그인 + 권한 체크
	@Query("SELECT e FROM EmployeeVO e INNER JOIN e.dept d "
			+ "ON e.dept.deptNo = d.deptNo WHERE e.empNo = :username")
	EmployeeVO findByEmpNoWithDeptNo(int username);
	
	EmployeeVO findByEmpNoAndEmpPwd(int username, String password);
}
