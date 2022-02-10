package com.metanet.service;

import java.util.List;

import com.metanet.domain.EduVO;
import com.metanet.domain.EmpListVO;

public interface WonwooEduService {
	
	public List<EduVO> eduList();

	public EduVO eduDetail(int edu_no);

	public void eduDelete(int edu_no);

	public void eduUpdate(EduVO eduVO);
	
	public List<EmpListVO> empList();
}
