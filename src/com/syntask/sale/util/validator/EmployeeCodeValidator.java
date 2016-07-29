package com.syntask.sale.util.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

import com.syntask.sale.entity.Employee;

@org.jboss.seam.annotations.faces.Validator
@Name(value = "employeeCodeValidator")
@BypassInterceptors
public class EmployeeCodeValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		if(value.toString().length() < 3)
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Length of code is at least 3"));
		
		EntityManager entityManager = (EntityManager) Component.getInstance("entityManager");
		if(entityManager.createNamedQuery("findByCode", Employee.class).setParameter("code", value.toString()).getResultList().size() > 0)
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Employee is exist"));
		
	}

}
