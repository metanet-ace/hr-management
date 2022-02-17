package com.metanet.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.DepartmentVO;
import com.metanet.domain.EmployeeVO2;

@Mapper
public interface EmployeeMapper2 {
	public int insertEmp(EmployeeVO2 emp);	//사원등록(인사팀)
	public EmployeeVO2 selectOne(int empNo);	//사원상세정보(공통)
	public int updateEmp(EmployeeVO2 emp);	//사원수정하기(인사팀) : 비밀번호수정x
	public int updatePwd(EmployeeVO2 emp);	//사원비밀번호 0000 변경(인사팀)
	public String pwCheck(int empNo);	//현재비밀번호 일치확인(공통)
	public void pwUpdate(Map<String, Object> map);	//비밀번호 수정(공통)
	
	public List<DepartmentVO> deptList(); //부서리스트조회(인사팀)
	public List<DepartmentVO> dept(); //부서이름, 번호 불러오기
	public int insertDept(DepartmentVO dept);	//부서 등록(인사팀)
	
	public int deptCount(int deptNo);	//부서별 인원 수
}
