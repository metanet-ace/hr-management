package com.metanet.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.EduVO;
import com.metanet.domain.EmpListVO;


@Mapper
public interface WonwooEduMapper{
	
	public List<EduVO> eduList();

	public EduVO eduDetail(int edu_no);

	public void eduDelete(int edu_no);

	public void eduUpdate(EduVO eduVO);

	public List<EmpListVO> empList();
}
