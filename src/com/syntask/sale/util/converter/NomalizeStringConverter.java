package com.syntask.sale.util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

import com.syntask.sale.util.StringUtil;

@org.jboss.seam.annotations.faces.Converter
@Name(value = "nomalizeStringConverter")
@BypassInterceptors
public class NomalizeStringConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(component.getId().equals("code"))
			return value.toUpperCase();
		return StringUtil.normalize(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value.toString();
	}

}
