package com.metanet.main;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EduHistoyVO {
	
	private int eduHisno;
	private int eduNO;
	private int empNO;
	private String eduFin;
	private int attendance;
	private int score;
}
