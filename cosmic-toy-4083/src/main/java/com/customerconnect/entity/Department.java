package com.customerconnect.entity;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;
	@Column(nullable = false, unique = true)
    private String departmentName;
    
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Operator> operators;

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(String departmentName, Set<Operator> operators) {
		super();
		this.departmentName = departmentName;
		this.operators = operators;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Set<Operator> getOperators() {
		return operators;
	}

	public void setOperators(Set<Operator> operators) {
		this.operators = operators;
	}

	@Override
	public int hashCode() {
		return Objects.hash(departmentId, departmentName, operators);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return departmentId == other.departmentId && Objects.equals(departmentName, other.departmentName)
				&& Objects.equals(operators, other.operators);
	}
    
	
    
}
