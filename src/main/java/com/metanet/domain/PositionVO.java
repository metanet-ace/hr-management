package com.metanet.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="POSITION")
public class PositionVO {
	@Id 
	private int posNo;
	private String posName;
	private int posMinsal;
	private int posMaxsal;
	
	@Transient
	private String empName;
	
	@Transient
	private int empNo;
	
	@Transient
	private Date empRetdate;
}
