package com.syntask.sale.controller;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.syntask.sale.util.Constains;

@Name(value = "managerDraffEmpController")
@Scope(ScopeType.CONVERSATION)
public class ManagerDraffEmpController extends ManagerEmployeeController{

	@Override
	public void initStatus() {
		status = Constains.EMP.DRAFF_STATUS;
	}

}
