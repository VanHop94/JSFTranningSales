package test.paging;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.web.RequestParameter;

import com.syntask.sale.entity.Employee;

@Name("personManager")
@Scope(ScopeType.SESSION)
public class PersonManager {

    @In(required = false)
    private PersonService personService;

   // @In(create = true)
    //@Out(required = false)
    public Employee person;

    @Out(required = false)
    public Employee searchPerson = new Employee();

    @RequestParameter
    public Long personId;

    public String currentVornameFilterValue;

    public String currentNachnameFilterValue;

    /*@In(create = true)
    public PersonPaginatingDataModel personPaginatingDataModel;*/

    public PersonManager() {
    }

    public Employee getPerson() {
        return person;
    }

    public void setPerson(Employee person) {
        this.person = person;
    }

    public Employee getSearchPerson() {
        return searchPerson;
    }

    public void setSearchPerson(Employee searchPerson) {
        this.searchPerson = searchPerson;
    }

    /*public PersonPaginatingDataModel getPersonPaginatingDataModel() {
        return personPaginatingDataModel;
    }

    public void setPersonPaginatingDataModel(PersonPaginatingDataModel personPaginatingDataModel) {
        this.personPaginatingDataModel = personPaginatingDataModel;
    }*/

    public String personenList() {
        return "personenList";
    }

    public String personAdd() {
        return "personAdd";
    }

    @Begin(join = true)
    @End
    public String personSave() throws Exception {
        try {
            if (person.getId() == null) {
                this.personService.save(person);
            } else {
                this.personService.merge(person);
            }
            return "personSave";
        } catch (Exception e) {
            throw e;
        }
    }

    public String personReset() throws Exception {
        try {
            person = new Employee();
            return "personReset";
        } catch (Exception e) {
            throw e;
        }
    }

    public String personEdit() throws Exception {
        try {
            person = this.personService.getPersonById(personId);
            return "personEdit";
        } catch (Exception e) {
            throw e;
        }
    }

    public String personenSearch() throws Exception {
        try {
            return "personenSearch";
        } catch (Exception e) {
            throw e;
        }
    }

    public List getPersonen() throws Exception {
        try {
            return this.personService.findAllPersons();
        } catch (Exception e) {
            throw e;
        }
    }

    public String getCurrentVornameFilterValue() {
        return currentVornameFilterValue;
    }

    public void setCurrentVornameFilterValue(String currentVornameFilterValue) {
        this.currentVornameFilterValue = currentVornameFilterValue;
    }

    public String getCurrentNachnameFilterValue() {
        return currentNachnameFilterValue;
    }

    public void setCurrentNachnameFilterValue(String currentNachnameFilterValue) {
        this.currentNachnameFilterValue = currentNachnameFilterValue;
    }

}
