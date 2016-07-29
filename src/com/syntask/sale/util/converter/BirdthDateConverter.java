package com.syntask.sale.util.converter;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;
@org.jboss.seam.annotations.faces.Converter
@Name(value = "birdthDateConverter")
@BypassInterceptors
public class BirdthDateConverter implements Converter {

	@SuppressWarnings("deprecation")
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Date date = new Date();
		String [] token = value.split("-");
		if(token.length != 3)
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Date is invalid"));
		
		try	{
			date.setDate(Integer.valueOf(token[0]));
			date.setMonth(Integer.valueOf(token[1]) - 1);
			date.setYear(Integer.valueOf(token[2]) - 1900);
		} catch(Exception e){
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Date is invalid"));
		}
		return date;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		Date date = (Date) value;
		int day = date.getDate();
		int month = date.getMonth() + 1;
		int year = date.getYear() + 1900;

		return StringUtils.leftPad(String.valueOf(day), 2, "0") + "-"
				+ StringUtils.leftPad(String.valueOf(month), 2, "0") + "-" + year;

	}

}
