package com.syntask.sale.controller;

import javax.faces.model.SelectItem;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;
import org.jboss.seam.web.ServletContexts;

import com.syntask.sale.dao.EmployeeDao;
import com.syntask.sale.entity.Employee;
import com.syntask.sale.exception.handle.ResourceNotFoundException;
import com.syntask.sale.util.Constains;

@Name(value = "employeeController")
@Scope(ScopeType.CONVERSATION)
public class EmployeeController extends BaseController{

	private Employee employee;
	private EmployeeDao employeeDao;

	public String close() {
		Conversation.instance().end();
		return "/sumary.jsf?faces-redirect=true";
	}

	public String delete() {
		employeeDao.deleteEmployee(employee);
		Conversation.instance().end();
		return "/sumary.jsf?faces-redirect=true";
	}
	public String createNewEmployee() {
		//employee = new Employee(true);
		//employeeDao.insertEmployee(employee);
		Conversation.instance().end();
		return "/employee.jsf?faces-redirect=true";
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public SelectItem[] getGender() {
		return new SelectItem[] { new SelectItem(new Integer(Constains.EMP.MALE), "Male"), new SelectItem(new Integer(Constains.EMP.FEMALE), "Female") };
	}

	public SelectItem[] getTypeEmployee() {
		return new SelectItem[] { new SelectItem(new Integer(-1), "--Select one--"),
				new SelectItem(new Integer(0), "Domestic"), new SelectItem(new Integer(1), "Commercial"),
				new SelectItem(new Integer(2), "Dealer") };
	}
	
	@Override
	public EmployeeDao getDao() {
		if(employeeDao == null)
			employeeDao = (EmployeeDao) Component.getInstance("employeeDao");
		return employeeDao;
	}

	@Override
	public void initData() throws Exception {
		
		String id = ServletContexts.getInstance().getRequest().getParameter("id");
		if (id != null) {
			employee = employeeDao.findByIdEmp(Integer.valueOf(id));
			if (employee == null){
				throw new ResourceNotFoundException();
			}
			return;
		}

		String empCode = ServletContexts.getInstance().getRequest().getParameter("code");
		if (empCode == null)
			createNewEmployee();
		else {
			employee = employeeDao.findByCodeEmp(empCode);
			if (employee == null)
				throw new ResourceNotFoundException();
		}
	}
}
