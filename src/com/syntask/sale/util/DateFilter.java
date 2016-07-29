package com.syntask.sale.util;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.Name;

import com.syntask.sale.entity.Employee;

@Name(value = "dateFilter")
public class DateFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filterValue;

	public DateFilter() {
		filterValue = "";
	}

	@SuppressWarnings("deprecation")
	public boolean filter(Object current) {
		 
	        if (filterValue.length() == 0) 
	            return true;
	        
	        Date t = ((Employee)current).getBirdth();
	        
	        if(t == null)
	        	return false;
	        
	        int day = t.getDate();
			int month = t.getMonth() + 1;
			int year = t.getYear() + 1900;
	        String dateStr =  StringUtils.leftPad(String.valueOf(day), 2, "0") + "-"
					+ StringUtils.leftPad(String.valueOf(month), 2, "0") + "-" + year;
	        if (dateStr.startsWith(filterValue)) {
	            return true;
	        }else {
	            return false; 
	        }
	    }

	public String getFilterValue() {
		return filterValue;
	}

	public void setFilterValue(String filterValue) {
		this.filterValue = filterValue;
	}

}
