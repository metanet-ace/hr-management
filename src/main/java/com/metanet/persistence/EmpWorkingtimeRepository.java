package com.metanet.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metanet.domain.EmpWorkingtimeVO;

public interface EmpWorkingtimeRepository extends JpaRepository<EmpWorkingtimeVO, Integer>{
}
