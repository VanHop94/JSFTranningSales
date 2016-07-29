package com.syntask.sale.util.validator;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

@org.jboss.seam.annotations.faces.Validator
@Name(value = "birdthDateValidator")
@BypassInterceptors
public class BirdthDateValidator implements Validator{

	@SuppressWarnings("deprecation")
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		Date date = (Date) value;
		if(date.compareTo(new Date(0,1,1)) < 0 || date.compareTo(new Date()) > 0)
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Date is invalided"));
		
	}

}
