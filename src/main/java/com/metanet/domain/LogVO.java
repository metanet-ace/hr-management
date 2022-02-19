package com.metanet.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LogVO {

	private int logNo;
	
	private int empNo;
	
	private String logIp;
	
	private String logWriter;
	
	private String logDate;
	
	private String logTarget;
	
	private String logDesc;
	
}
