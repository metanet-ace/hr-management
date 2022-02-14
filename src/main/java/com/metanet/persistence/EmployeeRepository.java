package com.metanet.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.metanet.domain.EmployeeVO;

public interface EmployeeRepository extends JpaRepository<EmployeeVO, Integer>, QuerydslPredicateExecutor<EmployeeVO>{
	
	// 시큐리티 로그인 + 권한 체크 --> 한 명의 직원과 그 부서를 SELECT 
	@Query("SELECT e FROM EmployeeVO e INNER JOIN e.dept d "
			+ "ON e.dept.deptNo = d.deptNo WHERE e.empNo = :username")
	EmployeeVO findByEmpNoWithDeptNo(int username);
	
	// 부서별 사원 목록 출력 with paging
	@Query("SELECT e FROM EmployeeVO e INNER JOIN e.dept d "
			+ "ON e.dept.deptNo = d.deptNo WHERE e.dept.deptName LIKE %:deptName%")
	Page<EmployeeVO> findByEmpWithDeptName(String deptName, Pageable pageable);
	
	// 직급별 사원 목록 출력 with paging
	@Query("SELECT e FROM EmployeeVO e INNER JOIN e.pos p "
			+ "ON e.pos.posNo = p.posNo WHERE e.pos.posName LIKE %:posName%")
	Page<EmployeeVO> findByEmpWithPosName(String posName, Pageable pageable);
	
	// 이름별 사원 목록 출력 with paging
	Page<EmployeeVO> findByEmpNameContaining(String empName, Pageable pageable);
	
	// empNo로 사원 찾기
	EmployeeVO findByEmpNo(int empNo);
	}
