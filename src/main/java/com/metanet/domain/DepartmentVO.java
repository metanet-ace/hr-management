package com.metanet.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String deptName;
	private int deptHead;
}
