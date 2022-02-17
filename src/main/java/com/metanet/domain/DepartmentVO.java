package com.metanet.domain;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="DEPARTMENT")
public class DepartmentVO {
	@Id @GeneratedValue
	private int deptNo;
	
	@NotBlank(message="부서명을 입력해주세요.")
	private String deptName;
	
	@NotNull(message="dddd")
	@Positive(message="부서장을 사원번호로 입력해주세요.")
	private int deptHead;
	
	@Transient
	private Date deptDate; 
	
	@Transient
	private Date deptModify;
	
	@Transient
	private String empName;
}
