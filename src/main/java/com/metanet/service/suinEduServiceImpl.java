package com.metanet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metanet.domain.AttendanceVO;
import com.metanet.domain.EduHistoryVO;
import com.metanet.domain.EduVO;
import com.metanet.domain.PageDTO;
import com.metanet.persistence.suinEduMapper;


@Service
public class suinEduServiceImpl implements suinEduService {
	
	@Autowired
	suinEduMapper mapper;

	@Override
	public void eduAdd(EduVO vo) {
		mapper.eduInsert(vo);
	}

	@Override
	public void eduAttendance() throws ParseException {
		List<AttendanceVO> list = mapper.eduHistorySelect();
		Calendar getToday = Calendar.getInstance();
		getToday.setTime(new Date());
		
		for(AttendanceVO vo : list) {
			Date startD = new SimpleDateFormat("yyyyMMdd").parse(vo.getEduStart());
			Date endD = new SimpleDateFormat("yyyyMMdd").parse(vo.getEduEnd());
			System.out.println(vo.getEduHisno() +" : "+ startD);
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			
			start.setTime(startD);
			end.setTime(endD);
			
			float diffSecBunja = (getToday.getTimeInMillis() - start.getTimeInMillis());
			float diffSecBunmo = (end.getTimeInMillis() - start.getTimeInMillis());
			
			vo.setAttendance((diffSecBunja/diffSecBunmo)*100);			
		}
		mapper.eduAttendanceUpdate(list);
	}

	@Override
	public List<EduHistoryVO> getEduHistoryList(PageDTO dto) {
		return mapper.eduHistoryListSelect(dto);
		 
	}

	@Override
	public List<EduHistoryVO> getEduHistoryListByKey(Map<String, String> map) {
		return mapper.eduHistoryListSelectByKey(map);
	}

	@Override
	public void eduHistoryAdd(Map<String, Object> param) {
		mapper.eduHistroyInsert(param);
	}

	@Override
	public void eduScoreUpdate(List<EduHistoryVO> list) {
		mapper.eduScoreUpdate(list);
	}

	@Override
	public void eduProgress() throws ParseException {
		List<EduVO> list = mapper.eduProgressList();

		Date today = new Date();
		
		for(EduVO vo : list) {
			Date startD = new SimpleDateFormat("yyyy-MM-dd").parse(vo.getEduStart());
			Date endD = new SimpleDateFormat("yyyy-MM-dd").parse(vo.getEduEnd());

			if(today.before(startD)) {
				vo.setEduProgress("pre");
			} else if (today.after(endD)) {
				vo.setEduProgress("post");
			} else {
				vo.setEduProgress("ing");
			}
		}
		mapper.eduProgressUpdate(list);
	}

	@Override
	public int eduHistoryTotalCount(Map<String, String> map) {
		return mapper.eduHistorytotalCount(map);
	}

}
