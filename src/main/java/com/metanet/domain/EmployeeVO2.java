package com.metanet.domain;



import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeVO2 {
	private int empNo;
	private int posNo;
	private int deptNo;
	private String empName;
	private String empGender;
	private String empPhone;
	private int empSal;
	private String empEmail;
	private Date empHiredate;
	private Date empRetdate;
	private String empSsc;
	private String empDegree;
	private String empCareer;
	private String empPwd;
	private String empRePhoto;
	private String empPhoto;
	private String empMil;
}
