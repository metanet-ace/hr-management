package com.metanet.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.metanet.domain.DepartmentVO;
import com.metanet.domain.DeptVO;
import com.metanet.domain.EmployeeVO2;
import com.metanet.domain.PositionVO;

@Mapper
public interface EmployeeMapper2 {
	public int insertEmp(EmployeeVO2 emp);	//사원등록(인사팀)
	public EmployeeVO2 selectOne(int empNo);	//사원상세정보(공통)
	public int updateEmp(EmployeeVO2 emp);	//사원수정하기(인사팀) : 비밀번호수정x
	public int updatePwd(EmployeeVO2 emp);	//사원비밀번호 0000 변경(인사팀)
	public String pwCheck(int empNo);	//현재비밀번호 일치확인(공통)
	public void pwUpdate(Map<String, Object> map);	//비밀번호 수정(공통)
	
	public List<DepartmentVO> deptList(); //부서리스트조회(인사팀)
	public List<DeptVO> dept(); //부서이름, 번호 불러오기
	public void insertDept(DeptVO dept);	//부서 등록(인사팀)
	public int empNoCheck(int empNo);	//사원번호 확인
	public List<DepartmentVO> PagingSelectDept(Map<String, Object> map);	//부서원 상세정보(인사팀)
	public int deptTotalCount(Map<String, Object> map);
	public DepartmentVO selectD(int deptNo);	//부서장 상세보기
	public void updateDept(DeptVO dept); 	//부서수정(인사팀)
	
	public int empDept(EmployeeVO2 emp);	//부서 삭제 전 사원들의 부서이동
	public int deleteDept(int deptNo);	//부서삭제
	
	public List<PositionVO> posList();	//직급리스트(인사팀)
	public List<PositionVO> pos();	// 직급이름, 번호 불러오기
	public void insertPos(PositionVO pos);	//직급추가(인사팀)
	public PositionVO selectSal(int posNo);	//직급상세(연봉)
	public List<PositionVO> PagingSelectPos(Map<String, Object> map);	//직급상세(사원)
	public int posTotalCount(Map<String, Object> map);
	public void updatePos(PositionVO pos);	//직급수정(인사팀)
	public int empPos(EmployeeVO2 emp);	//직급 삭제 전 사원들 직급0번이동
	public int deletePos(int posNo);	//직급삭제
}
