package com.metanet.domain;

import lombok.Data;

@Data
public class SearchDTO {
	// 검색 조건 
	private String searchCondition;
	// 검색어
	private String searchKeyword;
}
