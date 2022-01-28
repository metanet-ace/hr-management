package com.metanet.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.EduVO;


@Mapper
public interface WonwooEduMapper{
	
	public List<EduVO> eduList();
}
