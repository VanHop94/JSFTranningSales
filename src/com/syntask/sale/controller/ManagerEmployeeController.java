package com.syntask.sale.controller;

import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.Component;

import com.syntask.sale.dao.EmployeeDao;
import com.syntask.sale.entity.Employee;
import com.syntask.sale.entity.EmployeeFilter;
import com.syntask.sale.util.Constains;
import com.syntask.sale.util.Page;

public abstract class ManagerEmployeeController extends BaseController {

	protected List<Employee> employees;
	protected EmployeeFilter empFilter;
	protected String orderBy;
	protected int status;
	protected EmployeeDao employeeDao;
	protected Page page;

	public String getOrderBy() {
		return orderBy;
	}
	
	

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public EmployeeFilter getEmpFilter() {
		return empFilter;
	}

	public void setEmpFilter(EmployeeFilter empFilter) {
		this.empFilter = empFilter;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public abstract void initStatus();

	@Override
	public void initData() throws Exception {
		initStatus();
		employeeDao = (EmployeeDao) Component.getInstance("employeeDao");
		empFilter = new EmployeeFilter();
		page = new Page();
		orderBy = "emp_code";
		int numOfEmp = (int) employeeDao.numOfEmployee(empFilter, status);
		page.setNumOfPage(numOfEmp / page.getPageSize() + (numOfEmp % page.getPageSize() == 0 ? 0 : 1));
		getDataAtPage(page.getFirstPage());
	}

	public void filter() {
		page.setCurrentPage(page.getFirstPage());
		employees = employeeDao.filterEmp(empFilter, page, orderBy, status);
		int numOfEmp = (int) employeeDao.numOfEmployee(empFilter, status);
		page.setNumOfPage(numOfEmp / page.getPageSize() + (numOfEmp % page.getPageSize() == 0 ? 0 : 1));
	}

	public void nextPage() {
		page.setCurrentPage(page.getCurrentPage() + 1);
		getDataAtPage(page.getCurrentPage());
	}

	public void previousPage() {
		page.setCurrentPage(page.getCurrentPage() - 1);
		getDataAtPage(page.getCurrentPage());
	}

	public void firstPage() {
		getDataAtPage(page.getFirstPage());
	}

	public void lastPage() {
		getDataAtPage(page.getLastPage());
	}

	@Override
	public EmployeeDao getDao() {
		if (employeeDao == null)
			employeeDao = (EmployeeDao) Component.getInstance("employeeDao");
		return employeeDao;
	}

	public void getDataAtPage(int pageIndex) {
		page.setCurrentPage(pageIndex);
		employees = employeeDao.filterEmp(empFilter, page, orderBy, status);
	}

	/*
	 * public List<Employee> getEmployees(int pageIndex) { return
	 * employeeDao.getEmployees(page.getPageSize(), pageIndex, status); }
	 */

	public Page getPage() {
		return page;
	}
	
	public SelectItem[] getFilterGender() {
		return new SelectItem[] { new SelectItem("", "----Select gender----"),new SelectItem(Constains.EMP.STR_MALE, "Male"), new SelectItem(Constains.EMP.STR_FEMALE, "Female") };
	}

}
