package test.paging;

import java.util.HashMap;
import java.util.List;

import com.syntask.sale.entity.Employee;


public interface PersonService {

 public List<Employee> findAllPersons();
 public void save(Employee p);
 public void merge(Employee p);
 public void remove(Employee p);
 public Employee getPersonById(Long id);
 public Long getCount(HashMap<String,Object> filterMap);
 public List<Employee> getRange(Integer firstRow, Integer maxResult, String sortField, HashMap<String,Object> filterMap, boolean descending);
    
}
