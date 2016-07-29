package com.syntask.sale.util.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

@org.jboss.seam.annotations.faces.Converter
@Name(value = "employeeCodeConverter")
@BypassInterceptors
public class EmployeeCodeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pattern pattern = Pattern.compile("([a-zA-Z]+)([0-9]+)");
		Matcher matcher = pattern.matcher(value.toString());
		if(!matcher.find())
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Format code is {Prefix + code}, example: SA1001, EMP1001"));
		
		return value.toUpperCase();
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}

}
