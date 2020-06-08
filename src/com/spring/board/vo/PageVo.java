package com.spring.board.vo;

public class PageVo {
	
	private int pageNo = 0;
	private String searchTotal; 
	private String searchNormal;
	private String searchQnA;
	private String searchUnknown;
	private String searchFred;
	
	public String getSearchTotal() {
		return searchTotal;
	}

	public void setSearchTotal(String searchTotal) {
		this.searchTotal = searchTotal;
	}

	public String getSearchNormal() {
		return searchNormal;
	}

	public void setSearchNormal(String searchNormal) {
		this.searchNormal = searchNormal;
	}

	public String getSearchQnA() {
		return searchQnA;
	}

	public void setSearchQnA(String searchQnA) {
		this.searchQnA = searchQnA;
	}

	public String getSearchUnknown() {
		return searchUnknown;
	}

	public void setSearchUnknown(String searchUnknown) {
		this.searchUnknown = searchUnknown;
	}

	public String getSearchFred() {
		return searchFred;
	}

	public void setSearchFred(String searchFred) {
		this.searchFred = searchFred;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
}
