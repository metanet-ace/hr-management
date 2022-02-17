package com.metanet.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NoticeVO {
	
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeWriter;
	private String noticeDate;
	private int empNo;
	private String noticeFile;
	private String noticeRefile;
	
}
