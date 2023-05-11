package com.customerconnect.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Operator {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int operatorId;
	private String firstName;
	private String lastName;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false, unique = true)
	private String mobileNumber;
	private String city;
	
	@ManyToOne
    @JoinColumn(name = "departmentId")
    private Department department;
	
	@OneToOne(mappedBy = "operator", cascade = CascadeType.ALL)
	  private Login login;

	@OneToMany(mappedBy = "operator", cascade = CascadeType.ALL)
    private List<Issue> issues = new ArrayList<>();
	
    
	public Operator() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Operator(String firstName, String lastName, String email, String mobileNumber, String city,
			Department department, Login login, List<Issue> issues) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.city = city;
		this.department = department;
		this.login = login;
		this.issues = issues;
	}


	public int getOperatorId() {
		return operatorId;
	}


	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public Department getDepartment() {
		return department;
	}


	public void setDepartment(Department department) {
		this.department = department;
	}


	public Login getLogin() {
		return login;
	}


	public void setLogin(Login login) {
		this.login = login;
	}


	public List<Issue> getIssues() {
		return issues;
	}


	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}


	@Override
	public int hashCode() {
		return Objects.hash(city, department, email, firstName, issues, lastName, login, mobileNumber, operatorId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Operator other = (Operator) obj;
		return Objects.equals(city, other.city) && Objects.equals(department, other.department)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(issues, other.issues) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(login, other.login) && Objects.equals(mobileNumber, other.mobileNumber)
				&& operatorId == other.operatorId;
	}


	
	
}
