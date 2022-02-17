package com.metanet.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.AttendanceVO;
import com.metanet.domain.EduHistoryVO;
import com.metanet.domain.EduVO;
import com.metanet.domain.EmpListVO;
import com.metanet.domain.PageDTO;

@Mapper
public interface suinEduMapper {

	public void eduInsert(EduVO vo);

	public void eduAttendanceUpdate(List<AttendanceVO> list);

	public ArrayList<AttendanceVO> eduHistorySelect();

	public List<EduHistoryVO> eduHistoryListSelect(PageDTO dto);

	public List<EduHistoryVO> eduHistoryListSelectByKey(Map<String, String> map);

	public void eduHistroyInsert(Map<String, Object> param);

	public void eduScoreUpdate(List<EduHistoryVO> list);
	
	public List<EduVO> eduProgressList();
	
	public void eduProgressUpdate(List<EduVO> list);
	
	public int eduHistorytotalCount(Map<String,String> map);

	public int getTotalCountForAllocation(Map<String, String> map);

	public List<EmpListVO> empListForAllocationSelect(PageDTO pdto);

}
