package com.metanet.domain;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.ToString;

@Data
@ToString

public class EduVO {
	private int eduNo;
	
	@NotBlank(message="교육명을 입력해주세요.")
	private String eduTitle;
	
	@NotBlank(message="교육 상세를 입력해주세요.")
	private String eduDesc;
	
	@NotBlank(message="장소를 입력해주세요.")
	private String eduLoc;
	
	@NotBlank(message="총 이수 시간을 입력해주세요.")
	private String eduTime;
	
	@NotBlank(message="담당 강사를 입력해주세요.")
	private String eduTeacher;
	
	@NotBlank(message="인원을 입력해주세요.")
	private String eduPeople;
	
	@NotBlank(message="대상을 입력해주세요.")
	private String eduTarget;
	
	@NotBlank(message="시작일을 입력해주세요")
	private String eduStart;
	
	@NotBlank(message="종료일을 입력해주세요")
	private String eduEnd;
	
	@NotBlank(message="비용을 입력해주세요.")
	private String eduCost;
	
	private String eduProgress;
	
	private String eduFile;
	
	private String eduRefile;
	
}
