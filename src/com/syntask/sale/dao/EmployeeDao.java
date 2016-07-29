package com.syntask.sale.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.Name;

import com.syntask.sale.entity.Employee;
import com.syntask.sale.entity.EmployeeFilter;
import com.syntask.sale.util.Constains;
import com.syntask.sale.util.Page;

@Name(value = "employeeDao")
public class EmployeeDao extends BaseDao<Integer, Employee>{

	private static final long serialVersionUID = 1L;

	public EmployeeDao() {
		super();
	}

	public void insertEmployee(Employee emp) {
		persist(emp);
	}

	public void deleteEmployee(Employee emp) {
		delete(emp);
	}

	public Employee findByCodeEmp(String code) {
		List<Employee> datas = entityManager.createNamedQuery("findByCode", Employee.class).setParameter("code", code)
				.getResultList();
		if (datas.size() > 0) {
			return datas.get(0);
		}
		return null;
	}

	public void updateEmployee(Employee emp) {
		// entityManager.flush();
		// System.out.println("Dao");
		//update(emp);
		// System.out.println("Dao");
	}

	@SuppressWarnings("unchecked")
	public List<Employee> filterEmp(EmployeeFilter empFilter, Page page, String orderBy, int status) {
		int gender1 = -1; // does not match anything
		int gender2 = -1;

		if (empFilter.getFilterGender() != null || empFilter.getFilterGender().trim().length() > 0) {
			if (empFilter.getFilterGender().startsWith("m") && Constains.EMP.STR_MALE.contains(empFilter.getFilterGender().trim())) { // filter by male
				gender1 = gender2 = Constains.EMP.MALE;
			} else if (empFilter.getFilterGender().startsWith("f")
					&& Constains.EMP.STR_FEMALE.contains(empFilter.getFilterGender().trim())) { // filter by female
				gender2 = gender1 = Constains.EMP.FEMALE;
			}
		}

		if (empFilter.getFilterGender() == null || empFilter.getFilterGender().trim().length() == 0) { // do not filter
			gender1 = Constains.EMP.MALE;
			gender2 = Constains.EMP.FEMALE;
		}

		/*
		 * String queryStr =
		 * "SELECT e FROM Employee e where LOWER(e.empCode) LIKE :code AND LOWER(e.name) LIKE :name AND (e.gender = :gender1 OR e.gender = :gender2) AND e.status = :status"
		 * ; if(empFilter.isFilterByDay()) queryStr += " AND DAY('e.birdth') = "
		 * + empFilter.getFilterDay(); if(empFilter.isFilterByMonth()) queryStr
		 * += " AND MONTH('e.birdth') = " + empFilter.getFilterMonth();
		 * if(empFilter.isFilterByYear()) queryStr += " AND YEAR(e.birdth) = " +
		 * String.valueOf(empFilter.getFilterYear());
		 * 
		 * queryStr += " ORDER BY " + orderBy + " ASC";
		 */

		/*
		 * String queryStr =
		 * "SELECT * FROM DEMO_ALEX_EMPLOYEE where LOWER(emp_code) LIKE :code AND LOWER(name) LIKE :name AND (gender = :gender1 OR e.gender = :gender2) AND status = :status"
		 * ; if(empFilter.isFilterByDay()) queryStr += " AND DAY(birdth) = " +
		 * empFilter.getFilterDay(); if(empFilter.isFilterByMonth()) queryStr +=
		 * " AND MONTH(birdth) = " + empFilter.getFilterMonth();
		 * if(empFilter.isFilterByYear()) queryStr += " AND YEAR(birdth) = " +
		 * empFilter.getFilterYear();
		 */
		StringBuffer query = new StringBuffer();

		query.append("SELECT * FROM DEMO_ALEX_EMPLOYEE where ");

		if (empFilter.getFilterEmpCode().trim().length() > 0)
			query.append("LOWER(emp_code) LIKE '" + empFilter.getFilterEmpCode() + "%' AND ");
		if (empFilter.getFilterName().trim().length() > 0)
			query.append("LOWER(name) LIKE '" + empFilter.getFilterName() + "%' AND ");
		if (gender1 == gender2 && gender1 != -1)
			query.append("(gender = " + gender1 + " OR gender = " + gender2 + ") AND ");
		if (empFilter.isFilterByDay())
			query.append("DAY(birdth) = " + empFilter.getFilterDay() + " AND ");
		if (empFilter.isFilterByMonth())
			query.append("MONTH(birdth) = " + empFilter.getFilterMonth() + " AND ");
		if (empFilter.isFilterByYear())
			query.append("YEAR(birdth) = " + empFilter.getFilterYear() + " AND ");

		query.append(" status = " + status);
		query.append(" ORDER BY " + orderBy + " ASC");

		return entityManager.createNativeQuery(query.toString(), Employee.class)
				.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize()).setMaxResults(page.getPageSize())
				.getResultList();

		/*
		 * String queryStr1 =
		 * "select * from DEMO_ALEX_EMPLOYEE where YEAR(birdth) = 1994";
		 * 
		 * return entityManager.createNativeQuery(queryStr1, Employee.class)
		 * .setParameter("code", empFilter.getFilterEmpCode() + "%")
		 * .setParameter("name", empFilter.getFilterName() +
		 * "%").setParameter("gender1", gender1) .setParameter("gender2",
		 * gender2).setParameter("status", status)
		 * .setFirstResult((page.getCurrentPage() - 1) *
		 * page.getPageSize()).setMaxResults(page.getPageSize())
		 * .getResultList();
		 */

		/*
		 * return entityManager.createNativeQuery(queryStr, Employee.class)
		 * .setParameter("code", empFilter.getFilterEmpCode() + "%")
		 * .setParameter("name", empFilter.getFilterName() +
		 * "%").setParameter("gender1", gender1) .setParameter("gender2",
		 * gender2).setParameter("status", status)
		 * .setFirstResult((page.getCurrentPage() - 1) *
		 * page.getPageSize()).setMaxResults(page.getPageSize())
		 * .getResultList();
		 */
	}

	public long numOfEmployee(EmployeeFilter empFilter, int status) {
		int gender1 = -1; // does not match anything
		int gender2 = -1;

		if (empFilter.getFilterGender() != null || empFilter.getFilterGender().trim().length() > 0) {
			if (empFilter.getFilterGender().startsWith("m") && Constains.EMP.STR_MALE.contains(empFilter.getFilterGender().trim())) { // filter by male
				gender1 = gender2 = Constains.EMP.MALE;
			} else if (empFilter.getFilterGender().startsWith("f")
					&& Constains.EMP.STR_FEMALE.contains(empFilter.getFilterGender().trim())) { // filter by female
				gender2 = gender1 = Constains.EMP.FEMALE;
			}
		}

		if (empFilter.getFilterGender() == null || empFilter.getFilterGender().trim().length() == 0) { // do not filter
			gender1 = Constains.EMP.MALE;
			gender2 = Constains.EMP.FEMALE;
		}

		/*
		 * String queryStr =
		 * "SELECT count(e.id) FROM Employee e where LOWER(e.empCode) LIKE :code AND LOWER(e.name) LIKE :name AND (e.gender = :gender1 OR e.gender = :gender2) AND e.status = :status"
		 * ; if(empFilter.isFilterByDay()) queryStr += " AND DAY(e.birdth) = " +
		 * empFilter.getFilterDay(); if(empFilter.isFilterByMonth()) queryStr +=
		 * " AND MONTH(e.birdth) = " + empFilter.getFilterMonth();
		 * if(empFilter.isFilterByYear()) queryStr += " AND YEAR(e.birdth) = " +
		 * String.valueOf(empFilter.getFilterYear());
		 */

		// String queryStr = "SELECT count(*) FROM DEMO_ALEX_EMPLOYEE where
		// LOWER(emp_code) LIKE :code AND LOWER(name) LIKE :name AND (gender =
		// :gender1 OR gender = :gender2) AND status = :status";
		StringBuffer query = new StringBuffer();
		query.append("SELECT count(*) FROM DEMO_ALEX_EMPLOYEE where ");

		if (empFilter.getFilterEmpCode().trim().length() > 0)
			query.append("LOWER(emp_code) LIKE '" + empFilter.getFilterEmpCode() + "%' AND ");
		if (empFilter.getFilterName().trim().length() > 0)
			query.append("LOWER(name) LIKE '" + empFilter.getFilterName() + "%' AND ");
		if (gender1 == gender2 && gender1 != -1)
			query.append("(gender = " + gender1 + " OR gender = " + gender2 + ") AND ");
		if (empFilter.isFilterByDay())
			query.append("DAY(birdth) = " + empFilter.getFilterDay() + " AND ");
		if (empFilter.isFilterByMonth())
			query.append("MONTH(birdth) = " + empFilter.getFilterMonth() + " AND ");
		if (empFilter.isFilterByYear())
			query.append("YEAR(birdth) = " + empFilter.getFilterYear() + " AND ");
		query.append(" status = " + status);

		return (long) entityManager.createNativeQuery(query.toString(), Long.class).getSingleResult();
		/*
		 * return (long) entityManager.createNativeQuery(queryStr, Long.class)
		 * .setParameter("code", empFilter.getFilterEmpCode() + "%")
		 * .setParameter("name", empFilter.getFilterName() +
		 * "%").setParameter("gender1", gender1) .setParameter("gender2",
		 * gender2).setParameter("status", status).getSingleResult();
		 */

		// String queryStr1 = "select COUNT(*) from DEMO_ALEX_EMPLOYEE where
		// YEAR(birdth) = 1994";
		// return (long) entityManager.createNativeQuery(queryStr1,
		// Long.class).getSingleResult();
	}

	public Employee findByIdEmp(int id) {
		return findById(id);
	}

	public EntityManager getEm() {
		return entityManager;
	}

}
