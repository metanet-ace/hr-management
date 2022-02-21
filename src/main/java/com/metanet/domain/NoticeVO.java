package com.metanet.domain;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NoticeVO {
	
	private int noticeNo;
	
	private int empNo;
	
	@NotBlank(message="제목을 입력해주세요.")
	private String noticeTitle;
	
	@NotBlank(message="내용을 입력해주세요.")
	private String noticeContent;
	
	private String noticeWriter;
	
	private String noticeDate;
	
	private String noticeFile;
	
	private String noticeRefile;
	
}
