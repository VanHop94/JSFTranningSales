package test.paging;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("testPaging")
@Scope(ScopeType.CONVERSATION)
public class testPaging {

	private PersonPaginatingDataModel personPaginatingDataModel;

	@Create
	public void init() {
		personPaginatingDataModel = new PersonPaginatingDataModel();
	}

	public PersonPaginatingDataModel getPersonPaginatingDataModel() {
		return personPaginatingDataModel;
	}

	public void setPersonPaginatingDataModel(PersonPaginatingDataModel personPaginatingDataModel) {
		this.personPaginatingDataModel = personPaginatingDataModel;
	}

}
