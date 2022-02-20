package com.metanet.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metanet.domain.PositionVO;

public interface PositionRepository extends JpaRepository<PositionVO, Integer> {

}
