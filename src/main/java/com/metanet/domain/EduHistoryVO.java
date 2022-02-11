package com.metanet.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EduHistoryVO {
	
	private String eduTitle;
	private int empNo;
	private String empName;
	private int attendance;
	private int score;
}
