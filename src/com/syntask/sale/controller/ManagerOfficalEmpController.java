package com.syntask.sale.controller;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.syntask.sale.util.Constains;

@Name(value = "managerOfficalEmpController")
@Scope(ScopeType.CONVERSATION)
public class ManagerOfficalEmpController extends ManagerEmployeeController{

	@Override
	public void initStatus() {
		status = Constains.EMP.OUTSTANDING_STATUS;
	}


}
