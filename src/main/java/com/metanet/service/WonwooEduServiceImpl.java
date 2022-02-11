package com.metanet.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metanet.domain.EduVO;
import com.metanet.domain.EmpListVO;
import com.metanet.domain.PageDTO;
import com.metanet.persistence.WonwooEduMapper;

@Service
public class WonwooEduServiceImpl implements WonwooEduService {

	@Autowired
	WonwooEduMapper wonwooEduMapper;
	
	@Override
	public List<EduVO> eduList() {
		return wonwooEduMapper.eduList();
	}

	@Override
	public EduVO eduDetail(int edu_no) {
		return wonwooEduMapper.eduDetail(edu_no);
	}

	@Override
	public void eduDelete(int edu_no) {
		wonwooEduMapper.eduDelete(edu_no);
		
	}

	@Override
	public void eduUpdate(EduVO eduVO) {
		wonwooEduMapper.eduUpdate(eduVO);
	}

	@Override
	public List<EmpListVO> getPagingList(PageDTO pdto) {
		return wonwooEduMapper.getPagingList(pdto);
	}

	@Override
	public int totalCount(PageDTO pdto) {
		return wonwooEduMapper.totalCount(pdto);
	}

	@Override
	public List<EmpListVO> empList() {
		return wonwooEduMapper.empList();
	}

}
