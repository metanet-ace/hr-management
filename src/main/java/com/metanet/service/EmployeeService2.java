package com.metanet.service;

import java.util.Map;

import org.springframework.validation.Errors;

import com.metanet.domain.EmployeeVO2;

public interface EmployeeService2 {
	public int insertEmp(EmployeeVO2 emp); // 사원등록(인사팀)
	public EmployeeVO2 selectOne(int empNo);	//사원상세정보
	public int updateEmp(EmployeeVO2 emp);	// 사원수정하기(인사팀) : 비밀번호수정x
	public int updatePwd(EmployeeVO2 emp);	// 사원비밀번호 0000 수정(인사팀)
	public Map<String, String> validateHandling(Errors errors);	//유효성검사
	public String pwCheck(int empNo);
	public void pwUpdate(int empNo, String hashedPw);
}
