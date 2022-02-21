package com.metanet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.metanet.domain.EduHistoryVO;
import com.metanet.domain.EduVO;
import com.metanet.domain.EmpListVO;
import com.metanet.domain.LogVO;
import com.metanet.domain.NoticeVO;
import com.metanet.domain.PageDTO;
import com.metanet.persistence.WonwooEduMapper;

@Service
public class WonwooEduServiceImpl implements WonwooEduService {

	@Autowired
	WonwooEduMapper wonwooEduMapper;

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
	public void eduAdd(EduVO eduVO) {
		wonwooEduMapper.eduAdd(eduVO);

	}

	@Override
	public void eduUpdateNoModifyFile(EduVO eduVO) {
		wonwooEduMapper.eduUpdateNoModifyFile(eduVO);
	}

	// 유효성 검사에 실패한 필드가 있다면, Service 게층으로 Errors 객체를 전달하여 비즈니스 로직을 구현
	@Override
	public Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap<>();

		for (FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}

		return validatorResult;
	}

	@Override
	public void eduAddNoFile(@Valid EduVO eduVO) {
		wonwooEduMapper.eduAddNoFile(eduVO);
	}

	@Override
	public List<EduVO> getPagingEmpEduList(Map<String, Object> map) {
		return wonwooEduMapper.getPagingEmpEduList(map);
	}

	@Override
	public int EmpEduTotalCount(Map<String, Object> map) {
		return wonwooEduMapper.EmpEduTotalCount(map);
	}

	@Override
	public List<NoticeVO> getPagingNoticeList(PageDTO pdto) {
		return wonwooEduMapper.getPagingNoticeList(pdto);
	}

	@Override
	public int noticeTotalCount(PageDTO pdto) {
		return wonwooEduMapper.noticeTotalCount(pdto);
	}

	@Override
	public void noticeAdd(NoticeVO noticeVO) {
		wonwooEduMapper.noticeAdd(noticeVO);

	}

	@Override
	public void noticeAddNoFile(NoticeVO noticeVO) {
		wonwooEduMapper.noticeAddNoFile(noticeVO);
	}

	@Override
	public NoticeVO noticeDetail(int noticeNo) {
		return wonwooEduMapper.noticeDetail(noticeNo);
	}

	@Override
	public void noticeDelete(int notice_no) {
		wonwooEduMapper.noticeDelete(notice_no);
	}

	@Override
	public void noticeUpdate(NoticeVO noticeVO) {
		wonwooEduMapper.noticeUpdate(noticeVO);

	}

	@Override
	public void noticeUpdateNoModifyFile(NoticeVO noticeVO) {
		wonwooEduMapper.noticeUpdateNoModifyFile(noticeVO);
	}

	@Override
	public List<LogVO> getPagingLogList(PageDTO pdto) {
		return wonwooEduMapper.getPagingLogList(pdto);
	}

	@Override
	public int logTotalCount(PageDTO pdto) {
		return wonwooEduMapper.logTotalCount(pdto);
	}

	@Override
	public void writeLog(LogVO logVO) {
		wonwooEduMapper.writeLog(logVO);
	}


	@Override
	public List<EduVO> empEduCalendarList(int empNo) {
		return wonwooEduMapper.empEduCalendarList(empNo);
	}

}
