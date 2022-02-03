package com.metanet.persistence;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.EduVO;

@Mapper
public interface suinEduMapper {

	public void eduInsert(EduVO vo);

}
