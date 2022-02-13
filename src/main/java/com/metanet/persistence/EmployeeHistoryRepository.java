package com.metanet.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.metanet.domain.EmpHistoryVO;

public interface EmployeeHistoryRepository extends JpaRepository<EmpHistoryVO, Integer>, QuerydslPredicateExecutor<EmpHistoryVO>  {

}
