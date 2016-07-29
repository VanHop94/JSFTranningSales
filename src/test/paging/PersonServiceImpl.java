package test.paging;

import java.util.HashMap;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.syntask.sale.entity.Employee;


@SuppressWarnings("unchecked")
@Name("personService")
public class PersonServiceImpl implements PersonService {

	@In(value = "personDAO", create = true)
	private PersonDAO personDAOImpl;

	public List<Employee> findAllPersons() {
		List<Employee> allPersons = personDAOImpl.findAll(Employee.class);
		return allPersons;
	}

	public void save(Employee person) {
		personDAOImpl.persist(person);
	}

	public void merge(Employee person) {
		personDAOImpl.merge(person);
	}

	public void remove(Employee person) {
		personDAOImpl.remove(person);
	}

	public Employee getPersonById(Long id) {
		Employee p = (Employee) personDAOImpl.findById(Employee.class, id);
		return p;
	}

	public Long getCount(HashMap<String, Object> filterMap) {
		Long count = personDAOImpl.getCount(Employee.class, filterMap);
		return count;
	}

	public List<Employee> getRange(Integer firstRow, Integer maxResult, String sortField,
			HashMap<String, Object> filterMap, boolean descending) {
		List<Employee> list = null;
		if (descending) {
			list = personDAOImpl.findByFilter(Employee.class, firstRow, maxResult, sortField, GenericDAO.SORT_ORDER.DESC,
					filterMap);
		} else {
			list = personDAOImpl.findByFilter(Employee.class, firstRow, maxResult, sortField, GenericDAO.SORT_ORDER.ASC,
					filterMap);
		}
		return list;
	}

}
