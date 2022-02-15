package com.metanet.service;

import java.util.List;
import java.util.Map;

import com.metanet.domain.EduHistoryVO;
import com.metanet.domain.EduVO;
import com.metanet.domain.EmpListVO;
import com.metanet.domain.PageDTO;

public interface WonwooEduService {
	
	public List<EduVO> eduList();

	public EduVO eduDetail(int edu_no);

	public void eduDelete(int edu_no);

	public void eduUpdate(EduVO eduVO);
	
	public List<EmpListVO> getPagingList(PageDTO pdto);

	public int totalCount(PageDTO pdto);

	public List<EmpListVO> empList();

	public List<EduHistoryVO> getEduEmpHistroyList(int empNo);

	public List<EduHistoryVO> getEduEmpHistroyListByKey(Map<String, Object> map);

	public void eduAdd(EduVO eduVO);

	public void eduUpdateNoModifyFile(EduVO eduVO);
}
