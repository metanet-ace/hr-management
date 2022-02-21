package com.metanet.domain;

import lombok.ToString;

@ToString
public class PaginationDTO {
	   
	   final private int PAGE_PER_BLOCK = 5; // 간격 (1-5페이지 , 6-10페이지...) 
	   final private int PAGE_PER_GROUP = PageDTO.getPagePerGroup(); // 한 페이지 당 보여줄 개수
	   private int total; // 총 게시글 수 
	   
	   private int startPage; // 시작 페이지 (1, 6, 11, ... )
	   private int endPage; // 끝 페이지(5, 10, 15, ...)
	   
	   private boolean hasPrev; // 1페이지인가?(=이전페이지 출력할건가) 
	   private boolean hasNext; // 마지막 페이징 그룹인가?(=다음페이지 출력할건가)
	   
	   // 총 게시글 수와 현재 페이지 정보만 있으면 페이지네이션이 가능하다.
	   public PaginationDTO(int total, PageDTO dto) {
	      this.total = total; 
	      
	      // double로 처리를 안하면 1 / 5 와 같이 int/int 의 결과는 0이 나와서 에러가 난다.
	      endPage = (int) Math.ceil(dto.getPageNum()/(double)PAGE_PER_BLOCK) * PAGE_PER_BLOCK;
	      startPage = endPage-PAGE_PER_BLOCK+1;
	      //System.out.println("시작페이지: " + startPage + "/ 끝페이지 " + endPage);
	      int realEnd = (int) Math.ceil(total/(double)PAGE_PER_GROUP); // 실제 마지막 페이지
	      //System.out.println(realEnd);
	      if(realEnd < endPage) {
	         this.endPage = realEnd;
	      }
	      
	      hasPrev = startPage > 1;
	      hasNext = this.endPage < realEnd;
	      //System.out.println(hasPrev + "    " + hasNext);
	   }

	public int getTotal() {
	      return total;
	   }

	   public void setTotal(int total) {
	      this.total = total;
	   }

	   public int getStartPage() {
	      return startPage;
	   }

	   public void setStartPage(int startPage) {
	      this.startPage = startPage;
	   }

	   public int getEndPage() {
	      return endPage;
	   }

	   public void setEndPage(int endPage) {
	      this.endPage = endPage;
	   }

	   public boolean isHasPrev() {
	      return hasPrev;
	   }

	   public void setHasPrev(boolean hasPrev) {
	      this.hasPrev = hasPrev;
	   }

	   public boolean isHasNext() {
	      return hasNext;
	   }

	   public void setHasNext(boolean hasNext) {
	      this.hasNext = hasNext;
	   }
	}
