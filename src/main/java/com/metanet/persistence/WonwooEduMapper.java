package com.metanet.persistence;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.EduVO;


@Mapper
public interface WonwooEduMapper{

	void selectList(EduVO vo);
}
