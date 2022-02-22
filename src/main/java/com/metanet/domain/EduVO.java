package com.metanet.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

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
	
	@Positive(message="숫자만 입력 가능합니다.")
	private String eduTime;
	
	@NotBlank(message="담당 강사를 입력해주세요.")
	private String eduTeacher;
	
	@Positive(message="숫자만 입력 가능합니다.")
	private String eduPeople;
	
	@NotBlank(message="대상을 입력해주세요.")
	private String eduTarget;
	
	@NotBlank(message="시작일을 입력해주세요.")
	private String eduStart;
	
	@NotBlank(message="종료일을 입력해주세요")
	private String eduEnd;
	
	@Positive(message="숫자만 입력 가능합니다.")
	private String eduCost;
	
	private String eduProgress;
	
	private String eduFile;
	
	private String eduRefile;
	
}
