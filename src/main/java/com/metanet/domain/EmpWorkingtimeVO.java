package com.metanet.domain;

import java.util.Date;

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

@Data
@Entity
@Table(name="EMP_WORKINGHOUR")
@SequenceGenerator(
		  name = "SEQ_HOUR", 
		  sequenceName = "SEQ_HOUR_NO", // 매핑할 데이터베이스 시퀀스 이름 
		  initialValue = 1,
		  allocationSize = 1)
public class EmpWorkingtimeVO {
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, 
						generator= "SEQ_HOUR")
	private int workNo;
	@Temporal(TemporalType.DATE)
	private Date workStart;
	@Temporal(TemporalType.DATE)
	private Date workEnd;
	private String workType;

	@ManyToOne
	@JoinColumn(name = "EMP_NO")
	private EmployeeVO emp;
	
	@Transient
	private String formattedDate;
}
