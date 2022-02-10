package com.metanet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metanet.domain.AttendanceVO;
import com.metanet.domain.EduHistoyVO;
import com.metanet.domain.EduVO;
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
			
			Calendar start = Calendar.getInstance();
			Calendar end = Calendar.getInstance();
			
			start.setTime(startD);
			end.setTime(endD);
			
			float diffSecBunja = getToday.getTimeInMillis() - start.getTimeInMillis();
			float diffSecBunmo = end.getTimeInMillis() - start.getTimeInMillis();
			
			vo.setAttendance((diffSecBunja/diffSecBunmo)*100);			
		}
		mapper.eduAttendanceUpdate(list);
	}

}
