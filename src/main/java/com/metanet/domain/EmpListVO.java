package com.metanet.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmpListVO {
	
	private int empNo;
	private String empName;
	private String posName;
	private String deptName;
}
