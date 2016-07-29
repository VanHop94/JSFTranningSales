package com.syntask.sale.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeFilter {

	private String filterEmpCode;
	private String filterName;
	private String filterGender;
	private boolean isFilterByDay;
	private boolean isFilterByMonth;
	private boolean isFilterByYear;
	private int filterDay;
	private int filterMonth;
	private int filterYear;
	private String filterBirdth;

	public EmployeeFilter() {
		filterEmpCode = "";
		filterName = "";
		filterGender = "";
		filterBirdth = "";
		isFilterByDay = false;
		isFilterByMonth = false;
		isFilterByYear = false;
	}

	public boolean isFilterByBirdth() {
		return isFilterByDay || isFilterByMonth || isFilterByYear;
	}

	public int getFilterDay() {
		return filterDay;
	}

	public void setFilterDay(int filterDay) {
		this.filterDay = filterDay;
	}

	public int getFilterMonth() {
		return filterMonth;
	}

	public void setFilterMonth(int filterMonth) {
		this.filterMonth = filterMonth;
	}

	public int getFilterYear() {
		return filterYear;
	}

	public void setFilterYear(int filterYear) {
		this.filterYear = filterYear;
	}

	public boolean isFilterByDay() {
		return isFilterByDay;
	}

	public void setFilterByDay(boolean isFilterByDay) {
		this.isFilterByDay = isFilterByDay;
	}

	public boolean isFilterByMonth() {
		return isFilterByMonth;
	}

	public void setFilterByMonth(boolean isFilterByMonth) {
		this.isFilterByMonth = isFilterByMonth;
	}

	public boolean isFilterByYear() {
		return isFilterByYear;
	}

	public void setFilterByYear(boolean isFilterByYear) {
		this.isFilterByYear = isFilterByYear;
	}

	public void setFilterBirdth(String filterBirdth) {

		Pattern pattern = Pattern.compile("([0-9]{1,2}-)([0-9]{1,2}-)([0-9]{4})"); //16-11-1994
		Matcher matcher = pattern.matcher(filterBirdth);
		if (matcher.find()) {
			isFilterByDay = true;
			isFilterByMonth = true;
			isFilterByYear = true;
			filterDay = Integer.parseInt(matcher.group(1).replace("-", ""));
			filterMonth = Integer.parseInt(matcher.group(2).replace("-", ""));
			filterYear = Integer.parseInt(matcher.group(3).replace("-", ""));

		} else {
			pattern = Pattern.compile("([0-9]{1,2}-)([0-9]{4})"); //11-1994
			matcher = pattern.matcher(filterBirdth);
			if (matcher.find()) {
				isFilterByDay = false;
				isFilterByMonth = true;
				isFilterByYear = true;
				filterMonth = Integer.parseInt(matcher.group(1).replace("-", ""));
				filterYear = Integer.parseInt(matcher.group(2).replace("-", ""));
			} else {
				pattern = Pattern.compile("[0-9]{4}");  //1994
				matcher = pattern.matcher(filterBirdth);
				if (matcher.find()) {
					isFilterByDay = false;
					isFilterByMonth = false;
					isFilterByYear = true;
					filterYear = Integer.parseInt(filterBirdth);
				} else {
					isFilterByDay = false;
					isFilterByMonth = false;
					isFilterByYear = false;
				}
			}
		}

		this.filterBirdth = filterBirdth;
	}

	public String getFilterBirdth() {
		return filterBirdth;
	}

	public String getFilterEmpCode() {
		return filterEmpCode;
	}

	public void setFilterEmpCode(String filterEmpCode) {
		this.filterEmpCode = filterEmpCode.toLowerCase();
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName.toLowerCase();
	}

	public String getFilterGender() {
		return filterGender;
	}

	public void setFilterGender(String filterGender) {
		this.filterGender = filterGender.toLowerCase();
	}

}
