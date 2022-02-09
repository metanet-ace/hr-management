package com.metanet.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="POSITION")
public class PositionVO {
	@Id 
	private int posNo;
	private String posName;
	private int posMinsal;
	private int posMaxsal;
}
