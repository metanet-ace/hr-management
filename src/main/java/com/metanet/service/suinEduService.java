package com.metanet.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.metanet.domain.EduHistoryVO;
import com.metanet.domain.EduVO;
import com.metanet.domain.EmpListVO;
import com.metanet.domain.PageDTO;

public interface suinEduService {
	public void eduAdd(EduVO vo);
	public void eduAttendance() throws ParseException;
	public List<EduHistoryVO> getEduHistoryList(PageDTO dto);
	public List<EduHistoryVO> getEduHistoryListByKey(Map<String, String> map);
	public void eduHistoryAdd(Map<String, Object> param);
	public void eduScoreUpdate(List<EduHistoryVO> list);
	public void eduProgress() throws ParseException;
	public int eduHistoryTotalCount(Map<String, String> map);
	public int getTotalCountForAllocation(Map<String, String> map);
	public List<EmpListVO> empListForAllocationSelect(PageDTO pdto);
	public int getTotalCountEduList(Map<String, String> map);
	public List<EduVO> eduListSelect(PageDTO pdto);
	
}
