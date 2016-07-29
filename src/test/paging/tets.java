package test.paging;

import java.util.Date;

public class tets {

	public static void main(String [] args){
		
		Date date = new Date();
		//String str = "16-11-1994";
		
		date.setDate(16);
		date.setMonth(11 -1);
		date.setYear(1994 - 1900);
		
		System.out.println(date.toString());
		
	}
	
}
