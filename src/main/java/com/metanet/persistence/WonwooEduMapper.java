package com.metanet.persistence;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.EduVO;
import com.metanet.domain.LogVO;
import com.metanet.domain.NoticeVO;
import com.metanet.domain.PageDTO;

@Mapper
public interface WonwooEduMapper {

	
	public void eduAdd(EduVO eduVO);

	public void eduAddNoFile(@Valid EduVO eduVO);

	public EduVO eduDetail(int edu_no);

	public void eduUpdate(EduVO eduVO);

	public void eduUpdateNoModifyFile(EduVO eduVO);

	public void eduDelete(int edu_no);

	public List<EduVO> getPagingEmpEduList(Map<String, Object> map);

	public int EmpEduTotalCount(Map<String, Object> map);

	public List<NoticeVO> getPagingNoticeList(PageDTO pdto);

	public int noticeTotalCount(PageDTO pdto);

	public void noticeAdd(@Valid NoticeVO noticeVO);

	public void noticeAddNoFile(@Valid NoticeVO noticeVO);

	public NoticeVO noticeDetail(int noticeNo);

	public void noticeDelete(int notice_no);

	public void noticeUpdate(NoticeVO noticeVO);

	public void noticeUpdateNoModifyFile(NoticeVO noticeVO);

	public List<LogVO> getPagingLogList(PageDTO pdto);

	public int logTotalCount(PageDTO pdto);

	public void writeLog(LogVO logVO);

	public List<EduVO> empEduCalendarList(int empNo);

}
