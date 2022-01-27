package com.metanet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metanet.domain.EduVO;
import com.metanet.persistence.suinEduMapper;

@Service
public class suinEduServiceImpl implements suinEduService {
	
	@Autowired
	suinEduMapper mapper;

	@Override
	public int eduAdd(EduVO vo) {
		mapper.eduInsert(vo);
		return 0;
	}

}
