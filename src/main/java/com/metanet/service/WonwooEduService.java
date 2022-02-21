package com.metanet.service;

import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;

import com.metanet.domain.EduVO;
import com.metanet.domain.LogVO;
import com.metanet.domain.NoticeVO;
import com.metanet.domain.PageDTO;

public interface WonwooEduService {

	public void eduAdd(EduVO eduVO);

	public void eduAddNoFile(EduVO eduVO);

	public EduVO eduDetail(int edu_no);

	public void eduUpdate(EduVO eduVO);

	public void eduUpdateNoModifyFile(EduVO eduVO);

	public void eduDelete(int edu_no);

	public List<EduVO> getPagingEmpEduList(Map<String, Object> map);

	public int EmpEduTotalCount(Map<String, Object> map);

	public List<NoticeVO> getPagingNoticeList(PageDTO pdto);

	public int noticeTotalCount(PageDTO pdto);

	public void noticeAdd(NoticeVO noticeVO);

	public void noticeAddNoFile(NoticeVO noticeVO);

	public NoticeVO noticeDetail(int noticeNo);

	public void noticeDelete(int notice_no);

	public void noticeUpdate(NoticeVO noticeVO);

	public void noticeUpdateNoModifyFile(NoticeVO noticeVO);

	public Map<String, String> validateHandling(Errors errors); // 유효성 검사

	public List<LogVO> getPagingLogList(PageDTO pdto);

	public int logTotalCount(PageDTO pdto);
	
	public void writeLog(LogVO logVO);

	public List<EduVO> empEduCalendarList(int empNo);
}
