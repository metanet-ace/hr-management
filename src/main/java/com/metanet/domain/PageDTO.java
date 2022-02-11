package com.metanet.domain;

public class PageDTO {

	   final private static int PAGE_PER_GROUP = 10; // 페이지 당 보여즐 글 수
	   
	   private int pageNum; // 사용자가 위치한 페이지 번호 (parameter로 받아올 값)
	   
	   private int startNum; // 시작하는 게시글 번호 (Between 사이 값)
	   private int endNum; // 끝나는 게시글 번호 (Between 사이 값)   
	   
	   private String keyword;
	   private String searchContent;
	   
	   // 기본 1페이지 생성자
	   public PageDTO() {}
	   
	   public PageDTO(int pageNum, String keyword, String searchContent) {
	      this.pageNum = pageNum;
	      this.startNum = ((pageNum-1)*PAGE_PER_GROUP) + 1;  
	      this.endNum = pageNum*PAGE_PER_GROUP;
	      this.keyword = keyword;
	      this.searchContent = searchContent;
	   }
	   
	   public static int getPagePerGroup() {
	      return PAGE_PER_GROUP;
	   }
	   
	   public int getStartNum() {
	      return startNum;
	   }

	   public void setStartNum(int startNum) {
	      this.startNum = startNum;
	   }

	   public int getEndNum() {
	      return endNum;
	   }

	   public void setEndNum(int endNum) {
	      this.endNum = endNum;
	   }

	   public int getPageNum() {
	      return pageNum;
	   }

	   public void setPageNum(int pageNum) {
	      this.pageNum = pageNum;
	   }

	   public String getKeyword() {
	      return keyword;
	   }

	   public void setKeyword(String keyword) {
	      this.keyword = keyword;
	   }

	   public String getSearchContent() {
	      return searchContent;
	   }

	   public void setSearchContent(String searchContent) {
	      this.searchContent = searchContent;
	   }
	}
