package com.metanet.persistence;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.EduHistoryVO;
import com.metanet.domain.EduVO;
import com.metanet.domain.EmpListVO;
import com.metanet.domain.PageDTO;


@Mapper
public interface WonwooEduMapper{
	
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

	public void eduAddNoFile(@Valid EduVO eduVO);
}
