package com.metanet.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.EmpHistoryVO;
import com.metanet.domain.EmployeeVO;

@Mapper
public interface EmployeeMapper {
	// 사원 번호로 해당 사원 정보 출력
	public EmployeeVO findByEmpNo(int empNo);
	
	// 사원 부서, 직급 업데이트 
	public int updateEmp(EmployeeVO emp);
	
	// 사원 부서, 직급 업데이트 히스토리 저장
	public int saveHistory(EmpHistoryVO empHis);
	
	public List<Map<String, Object>> findWorkingDate(Map<String, String> map);
}
