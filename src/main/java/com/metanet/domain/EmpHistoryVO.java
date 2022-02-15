package com.metanet.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
@SequenceGenerator(
		  name = "SEQ_HISTORY", 
		  sequenceName = "SEQ_EMP_HISNO", // 매핑할 데이터베이스 시퀀스 이름 
		  initialValue = 1,
		  allocationSize = 1)
@Table(name="EMP_HISTORY")
public class EmpHistoryVO {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, 
						generator= "SEQ_HISTORY")
	private int empHisno;
//	private int empNo;
	private int posNo;
	private int deptNo;
	private String beforePos;
	private String beforeDept;
	@Temporal(TemporalType.DATE)
	private Date issuedDate;
	private String issuedOrder;
	private String issuedContent;
	
	@Transient
	private int batisEmpNo; 
	
	@ManyToOne
	@JoinColumn(name="EMP_NO")
	private EmployeeVO emp; 
}
