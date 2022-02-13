package com.metanet.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private int empNo;
	private int posNo;
	private int deptNo;
	private int beforePos;
	private int beforeDept;
	@Temporal(TemporalType.DATE)
	private Date issuedDate;
	private String issuedOrder;
	private String issuedContent;
	
}
