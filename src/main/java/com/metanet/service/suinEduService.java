package com.metanet.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.metanet.domain.EduHistoryVO;
import com.metanet.domain.EduVO;

public interface suinEduService {
	public void eduAdd(EduVO vo);
	public void eduAttendance() throws ParseException;
	public List<EduHistoryVO> getEduHistoryList();
	public List<EduHistoryVO> getEduHistoryListByKey(Map<String, String> map);
}
