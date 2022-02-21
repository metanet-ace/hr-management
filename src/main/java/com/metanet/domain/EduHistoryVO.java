package com.metanet.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EduHistoryVO {
	private int eduHisno;
	private int eduNo;
	private String eduTitle;
	private int empNo;
	private String empName;
	private int attendance;
	private String score;
}
