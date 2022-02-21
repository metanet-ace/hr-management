package com.metanet.service;

import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;

import com.metanet.domain.DepartmentVO;
import com.metanet.domain.DeptVO;
import com.metanet.domain.EmployeeVO2;
import com.metanet.domain.PositionVO;

public interface EmployeeService2 {
	public int insertEmp(EmployeeVO2 emp); // 사원등록(인사팀)
	public EmployeeVO2 selectOne(int empNo);	//사원상세정보(공통)
	public int updateEmp(EmployeeVO2 emp);	// 사원수정하기(인사팀) : 비밀번호수정x
	public int updatePwd(EmployeeVO2 emp);	// 사원비밀번호 0000 수정(인사팀)
	public Map<String, String> validateHandling(Errors errors);	//유효성검사
	public String pwCheck(int empNo);	//현재비밀번호 일치확인(공통)
	public void pwUpdate(int empNo, String hashedPw); //비밀번호 수정(공통)
	
	public List<DepartmentVO> deptList(); //부서리트스조회(인사팀)
	public List<DeptVO> dept(); //부서이름, 번호 불러오기
	public void insertDept(DeptVO dept);	//부서등록(인사팀)
	public int empNoCheck(int empNo);	//사원번호 확인
	public List<DepartmentVO> selectDept(int deptNo); //부서원 상세보기(인사팀)
	public DepartmentVO selectD(int deptNo);	//부서장 상세보기
	public void updateDept(DeptVO dept);	//부서수정(인사팀)
	
	public int empDept(EmployeeVO2 emp);	//부서 삭제 전 사원들의 부서이동
	public int deleteDept(int deptNo);	//부서삭제
	
	public List<PositionVO> posList();	// 직급리스트조회(인사팀)
	public List<PositionVO> pos();	//직급이름, 번호 불러오기
	public void insertPos(PositionVO pos);	//직급추가(인사팀)
	public PositionVO selectSal(int posNo);	//직급상세(연봉)
	public List<PositionVO> selectPos(int posNo);	//직급상세(사원)
	public void updatePos(PositionVO pos);	//직급수정(인사팀)
	public int empPos(EmployeeVO2 emp);	//직급 삭제 전 사원들 직급0번이동
	public int deletePos(int posNo);	//직급삭제
}
