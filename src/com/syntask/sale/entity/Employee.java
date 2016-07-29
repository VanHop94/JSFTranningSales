package com.syntask.sale.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.jboss.seam.annotations.Name;

import com.syntask.sale.util.Constains;

@Entity
@Name(value = "employee")
@Table(name = "demo_alex_employee")
@NamedQueries({ @NamedQuery(name = "getEmployees", query = "SELECT e " + "FROM Employee e"),
				@NamedQuery(name = "filterEmployee", query = "SELECT e FROM Employee e where LOWER(e.empCode) LIKE :code AND LOWER(e.name) LIKE :name AND (e.gender = :gender1 OR e.gender = :gender2) AND e.status = :status ORDER BY :orderBy ASC"),
				@NamedQuery(name = "countEmployee", query = "SELECT count(e.id) FROM Employee e where LOWER(e.empCode) LIKE :code AND LOWER(e.name) LIKE :name AND (e.gender = :gender1 OR e.gender = :gender2) AND e.status = :status"),
				@NamedQuery(name = "findByCode", query = "SELECT e FROM Employee e where e.empCode = :code"),
})
public class Employee {
	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "emp_code")
	private String empCode;

	@Column(name = "name")
	private String name;

	@Column(name = "birdth")
	private Date birdth;

	@Column(name = "short_name")
	private String shortName;

	@Column(name = "gender")
	private Integer gender; // 0 = male, 1 = female

	@Column(name = "is_english_language")
	private boolean hasEnglishLanguage; // 0 = false, 1 = true

	@Column(name = "is_china_language")
	private boolean hasChinaLanguage; // the same above

	@Column(name = "type")
	private Integer type; // 0 = Domestic, 1 = Commercial, 2 = Dealer

	@Column(name = "address")
	private String address;

	@Column(name = "status")
	private int status;

	public Employee() {
	}
	
	public boolean isShowGender(){
		if(gender == null)
			return false;
		return true;
	}

	public Employee(boolean isInit) {
		super();
		if (isInit) {
			empCode = "";
			name = "";
			//birdth = new Date(1990 - 1900, 0, 1);
			shortName = "";
			//gender = 0;
			hasEnglishLanguage = false;
			hasChinaLanguage = false;
			//type = 0;
			address = "";
			status = Constains.EMP.DRAFF_STATUS;
		}
	}

	public Employee(Integer id, String empCode, String name, Date birdth, String shortName, Integer gender,
			boolean hasEnglishLanguage, boolean hasChinaLanguage, int type, String address, int status) {
		super();
		this.id = id;
		this.empCode = empCode;
		this.name = name;
		this.birdth = birdth;
		this.shortName = shortName;
		this.gender = gender;
		this.hasEnglishLanguage = hasEnglishLanguage;
		this.hasChinaLanguage = hasChinaLanguage;
		this.type = type;
		this.address = address;
		this.status = status;
	}

	public Employee(String empCode, String name, Date birdth, String shortName, Integer gender,
			boolean hasEnglishLanguage, boolean hasChinaLanguage, int type, String address, int status) {
		super();
		this.empCode = empCode;
		this.name = name;
		this.birdth = birdth;
		this.shortName = shortName;
		this.gender = gender;
		this.hasEnglishLanguage = hasEnglishLanguage;
		this.hasChinaLanguage = hasChinaLanguage;
		this.type = type;
		this.address = address;
		this.status = status;
	}

	public boolean isHasEnglishLanguage() {
		return hasEnglishLanguage;
	}

	public void setHasEnglishLanguage(boolean hasEnglishLanguage) {
		this.hasEnglishLanguage = hasEnglishLanguage;
	}

	public boolean isHasChinaLanguage() {
		return hasChinaLanguage;
	}

	public void setHasChinaLanguage(boolean hasChinaLanguage) {
		this.hasChinaLanguage = hasChinaLanguage;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		updateStatus();
	}

	public Date getBirdth() {
		return birdth;
	}

	public void setBirdth(Date birdth) {
		this.birdth = birdth;
		updateStatus();
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
		updateStatus();
	}

	public Integer getGender() {
		if(gender == null)
			return -1;
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
		updateStatus();
	}

	public int getType() {
		if(type == null)
			return -1;
		return type;
	}

	public void setType(int type) {
		this.type = type;
		updateStatus();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		updateStatus();
	}
	
	private void updateStatus(){
		if(getStatus() == Constains.EMP.DRAFF_STATUS && isSufficientInfo())
			setStatus(Constains.EMP.OUTSTANDING_STATUS);
	}
	
	public boolean isSufficientInfo(){
		
		if(empCode.length() == 0 || name.length() == 0 || address.length() == 0 || shortName.length() == 0 || type == null || birdth == null || gender == null )
			return false;
		return true;
	}

}
