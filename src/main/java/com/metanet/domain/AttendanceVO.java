package com.metanet.domain;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttendanceVO {
	private int eduHisno;
	private String eduStart;
	private String eduEnd;
	private float attendance;

}
