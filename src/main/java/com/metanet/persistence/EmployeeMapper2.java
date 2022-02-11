package com.metanet.persistence;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.EmployeeVO2;

@Mapper
public interface EmployeeMapper2 {
	public int insertEmp(EmployeeVO2 emp);	//사원등록(인사팀)
	public EmployeeVO2 selectOne(int empNo);	//사원상세정보
	public int updateEmp(EmployeeVO2 emp);	//사원수정하기(인사팀) : 비밀번호수정x
	public int updatePwd(EmployeeVO2 emp);	//사원비밀번호 0000 변경(인사팀)
}
