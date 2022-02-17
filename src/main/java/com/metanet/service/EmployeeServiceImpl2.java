package com.metanet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.metanet.domain.DepartmentVO;
import com.metanet.domain.EmployeeVO2;
import com.metanet.persistence.EmployeeMapper2;

@Service
public class EmployeeServiceImpl2 implements EmployeeService2 {

	@Autowired
	EmployeeMapper2 empMapper;

	@Autowired
	PasswordEncoder encoder;

	@Override
	// 사원등록(인사팀)
	public int insertEmp(EmployeeVO2 emp) {
		emp.setEmpPwd(encoder.encode("0000"));
		return empMapper.insertEmp(emp);
	}

	@Override
	// 사원 상세정보
	public EmployeeVO2 selectOne(int empNo) {
		return empMapper.selectOne(empNo);
	}

	@Override
	// 사원수정하기(인사팀) : 비밀번호수정x
	public int updateEmp(EmployeeVO2 emp) {
		System.out.println("service에서 empNo확인" + emp.getEmpNo());
		return empMapper.updateEmp(emp);
	}

	@Override
	// 사원비밀번호 0000수정(인사팀)
	public int updatePwd(EmployeeVO2 emp) {
		emp.setEmpPwd(encoder.encode("0000"));
		return empMapper.updatePwd(emp);
	}

	// 유효성 검사에 실패한 필드가 있다면, Service 게층으로 Errors 객체를 전달하여 비즈니스 로직을 구현
	public Map<String, String> validateHandling(Errors errors) {
		Map<String, String> validatorResult = new HashMap<>();

		for (FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}

		return validatorResult;
	}

	// 현재비밀번호 일치확인(공통)
	@Override
	public String pwCheck(int empNo) {
		return empMapper.pwCheck(empNo);
	}

	// 비밀번호 수정(공통)
	@Override
	public void pwUpdate(int empNo, String hashedPw) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empNo", empNo);
		map.put("empPwd", hashedPw);
		empMapper.pwUpdate(map);
	}

	// 부서리스트(인사팀)
	@Override
	public List<DepartmentVO> deptList() {
		return empMapper.deptList();
	}

	// 부서이름, 번호 불러오기
	@Override
	public List<DepartmentVO> dept() {
		return empMapper.dept();
	}

	// 부서별 인원수
	@Override
	public int deptCount(int deptNo) {
		return empMapper.deptCount(deptNo);
	}

	// 부서등록(인사팀)
	@Override
	public int insertDept(DepartmentVO dept) {
		return empMapper.insertDept(dept);
	}
}
