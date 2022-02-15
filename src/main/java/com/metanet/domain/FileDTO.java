package com.metanet.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileDTO {
	private String uuid; // unique한 파일 이름을 만들기 위한 속성
	private String fileName; //실제 파일 이름
	private String contentType;
	
	public FileDTO() {}
	
	public FileDTO(String uuid, String fileName, String contentType) {
		this.uuid = uuid;
		this.fileName = fileName;
		this.contentType = contentType;
		System.out.println(contentType);
	}
	
}
