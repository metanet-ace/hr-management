package com.metanet.persistence;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.AttendanceVO;
import com.metanet.domain.EduHistoyVO;
import com.metanet.domain.EduVO;

@Mapper
public interface suinEduMapper {

	public void eduInsert(EduVO vo);

	public void eduAttendanceUpdate(List<AttendanceVO> list);

	public ArrayList<AttendanceVO> eduHistorySelect();

}
