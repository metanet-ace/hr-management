package com.metanet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metanet.domain.EduVO;
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

}
