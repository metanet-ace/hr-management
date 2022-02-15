package com.metanet.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.metanet.domain.QEmpHistoryVO;
import com.metanet.domain.QEmployeeVO;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QuerydslRepository {
	
	private final JPAQueryFactory queryFactory;
	
//	public List<Tuple> findByEmpHistoryUsingQuerydsl(){
//		
//		QEmpHistoryVO history = QEmpHistoryVO.empHistoryVO;
//		QEmployeeVO employee = QEmployeeVO.employeeVO;
//		
//		return queryFactory.select(history, employee.empName)
//							.from(history)
//							.join(employee)
//							.on(history.empNo.eq(employee.empNo))
//							.orderBy(history.issuedDate.desc())
//							.offset(0)
//							.limit(5)
//							.fetch();
//	}
}
