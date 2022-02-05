package com.metanet.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="EMPLOYEE")
public class EmployeeVO {
	@Id
	private int empNo;
	private int posNo;
	private int deptNo;
	private String empName;
	private String empGender;
	private String empPhone;
	private int empSal;
	private String empEmail;
	@Temporal(TemporalType.DATE)
	private Date empHiredate;
	@Temporal(TemporalType.DATE)
	private Date empRetdate;
	private String empSsc;
	private String empDegree;
	private String empCareer;
	private String empPwd;
	private String empPhoto;
	private String empMil;
}