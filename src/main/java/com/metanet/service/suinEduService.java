package com.metanet.service;

import java.text.ParseException;

import com.metanet.domain.EduHistoyVO;
import com.metanet.domain.EduVO;

public interface suinEduService {
	public void eduAdd(EduVO vo);
	public void eduAttendance(EduHistoyVO vo) throws ParseException;
}
