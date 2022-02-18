package com.metanet.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.validation.Errors;

import com.metanet.domain.EduHistoryVO;
import com.metanet.domain.EduVO;
import com.metanet.domain.EmpListVO;
import com.metanet.domain.NoticeVO;
import com.metanet.domain.PageDTO;

public interface WonwooEduService {
	
	public List<EduVO> eduList();

	public EduVO eduDetail(int edu_no);

	public void eduDelete(int edu_no);

	public void eduUpdate(EduVO eduVO);
	
	public List<EmpListVO> getPagingList(PageDTO pdto);

	public List<EduVO> getPagingEmpEduList(Map<String, Object> map);
	
	public List<EduVO> getPagingEduList(PageDTO pdto);
	
	public List<NoticeVO> getPagingNoticeList(PageDTO pdto);
	
	public int totalCount(PageDTO pdto);
	
	public int EmpEduTotalCount(Map<String, Object> map);
	
	public int EduTotalCount(PageDTO pdto);
	
	public int noticeTotalCount(PageDTO pdto);

	public List<EmpListVO> empList();

	public List<EduHistoryVO> getEduEmpHistroyList(int empNo);

	public List<EduHistoryVO> getEduEmpHistroyListByKey(Map<String, Object> map);

	public void eduAdd(EduVO eduVO);

	public void eduUpdateNoModifyFile(EduVO eduVO);

	public Map<String, String> validateHandling(Errors errors); //유효성검사

	public void eduAddNoFile(EduVO eduVO);

	public void noticeAdd(NoticeVO noticeVO);

	public void noticeAddNoFile(NoticeVO noticeVO);

	public NoticeVO noticeDetail(int noticeNo);

	public void noticeDelete(int notice_no);

	public void noticeUpdate(NoticeVO noticeVO);

	public void noticeUpdateNoModifyFile(NoticeVO noticeVO);
}
