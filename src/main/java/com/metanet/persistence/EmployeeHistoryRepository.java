package com.metanet.persistence;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.metanet.domain.EmpHistoryVO;

public interface EmployeeHistoryRepository extends JpaRepository<EmpHistoryVO, Integer>, QuerydslPredicateExecutor<EmpHistoryVO>  {
	public Page<EmpHistoryVO> findByEmpEmpNameContaining(String empName, Pageable pageable); 
	public Page<EmpHistoryVO> findByIssuedDateBetween(Date startdate, Date enddate, Pageable pageable);
}
