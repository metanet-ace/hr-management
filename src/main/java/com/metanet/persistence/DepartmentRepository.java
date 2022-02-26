package com.metanet.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metanet.domain.DepartmentVO;

public interface DepartmentRepository extends JpaRepository<DepartmentVO, Integer> {

	DepartmentVO findByDeptNo(int deptNo);
}
