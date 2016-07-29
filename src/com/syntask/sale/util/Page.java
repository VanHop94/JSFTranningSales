package com.syntask.sale.util;

import java.util.ArrayList;
import java.util.List;

public class Page {

	private int pageSize;
	private int currentPage;
	private int numOfPage;
	private int lastPage;
	private int firstPage;
	private List<Integer> tempPage;

	public Page(){
		pageSize = Constains.PAGE.PAGE_SIZE;
		currentPage = 1;
		firstPage = 1;
		tempPage = new ArrayList<>();
	}
	
	public void updateTempPage(){
		
		if(numOfPage == 0){
			tempPage.clear();
			return;
		}
		
		int start = currentPage - pageSize/2 < 1 ? 1 : currentPage - pageSize/2;
		int step = Constains.PAGE.NUM_OF_AROUND_PAGE - (currentPage - start);
		int finish = currentPage + step > numOfPage ? numOfPage : currentPage + step;
		
		if(finish == numOfPage){
			start = start - (currentPage + pageSize/2 - finish) >= 1 ? start - (currentPage + pageSize/2 - finish) : start;
		}
		
		tempPage.clear();
		for(int i = start ; i <= finish ; i++){
			tempPage.add(i);
		}
		
	}
	
	public int getLastTempPage(){
		if(tempPage.size() == 0)
			return 0;
		return tempPage.get(tempPage.size() - 1);
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		updateTempPage();
	}

	public int getNumOfPage() {
		return numOfPage;
	}

	public void setNumOfPage(int numOfPage) {
		this.numOfPage = numOfPage;
		setCurrentPage(firstPage);
		setLastPage(numOfPage);
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public List<Integer> getTempPage() {
		return tempPage;
	}

	public void setTempPage(ArrayList<Integer> tempPage) {
		this.tempPage = tempPage;
	}

}
