package com.metanet.domain;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeVO2 {
	private int empNo;
	
	@Positive(message="직급을 선택해주세요.")
	private String posNo;
	
	@Positive(message="부서를 선택해주세요.")
	private String deptNo;
	
	@NotBlank(message="이름을 입력해주세요.")
	private String empName;
	
	@NotBlank(message="성별을 선택해주세요.")
	private String empGender;
	
	@Pattern(regexp="^\\d{2,3}-\\d{3,4}-\\d{4}$", message="전화번호 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
	private String empPhone;
	
	@Positive(message="연봉을 입력해주세요.")
	private String empSal;
	
	@Pattern(regexp="^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$", message="이메일 형식에 맞지 않습니다.")
	private String empEmail;
	
	@NotBlank(message="날짜를 입력해주세요.")
	private String empHiredate;
	
	private String empRetdate;
	
	@Pattern(regexp="\\d{2}([0]\\d|[1][0-2])([0][1-9]|[1-2]\\d|[3][0-1])[-][1-4]\\d{6}", message="xxxxxx-x(1~4)xxxxxx 형식을 맞춰주세요.")
	private String empSsc;
	
	@NotBlank(message="학력을 입력해주세요.")
	private String empDegree;
	
	@NotBlank(message="신입/경력을 선택해주세요.")
	private String empCareer;
	
	private String empPwd;
	private String empRePhoto;
	
	private String empPhoto;
	
	@NotBlank(message="병역을 선택해주세요.")
	private String empMil;
	
	private String deptName;
	private String posName;
}
