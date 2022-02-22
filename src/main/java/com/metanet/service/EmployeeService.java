package com.metanet.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.metanet.domain.DepartmentVO;
import com.metanet.domain.EmpHistoryVO;
import com.metanet.domain.EmpWorkingtimeVO;
import com.metanet.domain.EmployeeVO;
import com.metanet.domain.PositionVO;

public interface EmployeeService {

	// 세션에 로그인 된 사원 정보 넣어주기
	EmployeeVO getLoginedEmp(int empNo);

	// 페이징 처리된 사원 전체 리스트 
	Page<EmployeeVO> getEmpList(Pageable pageable);

	// 페이징 처리되고 부서 검색으로 출력된 전체 리스트
	Page<EmployeeVO> getEmpListWithDept(String deptName, Pageable pageable);

	// 페이징 처리되고 직급 검색으로 출력된 전체 리스트
	Page<EmployeeVO> getEmpListWithPos(String posName, Pageable pageable);

	// 페이징 처리되고 이름 검색으로 출력된 사원 리스트
	Page<EmployeeVO> getEmpListWithName(String empName, Pageable pageable);

	// 부서 전체 출력 
	List<DepartmentVO> getDeptList();

	// 직급 전체 출력 
	List<PositionVO> getPosList();

	// 사원의 부서, 직급이동(UPDATE)
	int updateEmpDeptAndPos(int empNo, int deptNo, int posNo, String reason);

	// 사원의 부서이동(UPDATE)
	int updateEmpDept(int empNo, int deptNo, String reason);

	// 사원의 직급이동(UPDATE)
	int updateEmpPos(int empNo, int posNo, String reason);

	// 퇴사자 처리
	void updateRetire(int empNo, String retireReason);

	// 페이징 + 조건) 히스토리 리스트
	Page<EmpHistoryVO> getEmpHistoryList(HashMap<String, String> data, Pageable pageable);

	// 페이징 + 조건) 퇴사자 리스트
	Page<EmployeeVO> getEmpRetireList(HashMap<String, String> data, Pageable pageable);

	// 퇴사자 정보 출력
	Map<String, Object> getEmpRetireInfo(int empNo);

	// 퇴사 처리 취소
	EmployeeVO cancleRetire(int empNo);

	// 출근 시간 등록 
	EmpWorkingtimeVO insertStartTime(int empNo);

	// 오늘 출근한 시간 보기
	EmpWorkingtimeVO findStartWorkingTime(int empNo);

	// 퇴근 시간 등록
	EmpWorkingtimeVO insertEndTime(int empNo);

	// 퇴근 시간 등록 + 52시간 초과 시에
	EmpWorkingtimeVO insertEndTime(int empNo, Date time);

	// 오늘 퇴근한 시간 보기
	EmpWorkingtimeVO findEndWorkingTime(int empNo);

	// 근무 시간 기록 비즈니스 로직
	List<Map<String, Object>> selectWorkingTime(int empNo, Map<String, String> map);

	// 일주일 동안 근무한 총 시간 출력
	Integer findTotalTime(int empNo);

}