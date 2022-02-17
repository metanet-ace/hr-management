package com.metanet.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeptVO {
	private int deptNo;
	private String deptName;
	private int empNo;
	private Date deptDate; 
	private Date deptModify;
	private String empName;
}
