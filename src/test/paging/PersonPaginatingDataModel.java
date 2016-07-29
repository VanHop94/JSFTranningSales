package test.paging;

import java.util.HashMap;
import java.util.List;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;

import com.syntask.sale.entity.Employee;


/*@Name("personPaginatingDataModel")
@Scope(ScopeType.CONVERSATION)*/
public class PersonPaginatingDataModel extends PaginatingDataModel<Employee, Long> {
    
    private static final long serialVersionUID = 1868068053701471139L;
    
   /* @Create
    public void init(){
    	Conversation.instance().begin();
    }*/
    private PersonService personService;
    public PersonPaginatingDataModel() {
    	personService = (PersonService) Component.getInstance("personService");
	}
    
   // @In(create = true, value = "personService")
   // private PersonService personService;

    /**
     * @see PaginatingDataModel#getId
     */
    @Override
    public Long getId(Employee person) {
        return Long.valueOf(person.getId().toString());
    }

    /**
     * @see PaginatingDataModel#findObjects
     */
    @Override
    public List<Employee> findObjects(int firstRow, int maxResult, String sortField, HashMap<String,Object> filterMap, boolean descending) {
        return personService.getRange(firstRow, maxResult, sortField, filterMap, descending);
    }

    /**
     * @see PaginatingDataModel#getObjectById(java.lang.Object)
     */
    @Override
    public Employee getObjectById(Long id) {
        return personService.getPersonById(id);
    }

    /**
     * 
     * @see PaginatingDataModel#getNumRecords
     */
    @Override
    public Long getNumRecords(HashMap<String,Object> filterMap) {
        return personService.getCount(filterMap);
    }
}
