package com.syntask.sale.exception.handle;

import org.jboss.seam.annotations.ApplicationException;
import org.jboss.seam.annotations.exception.Redirect;

@Redirect(viewId = "/error.jsf", end = true, message = "Resource not found!")
@ApplicationException(rollback = true)
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
