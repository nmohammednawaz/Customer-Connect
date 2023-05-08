package com.customerconnect.entity;

import java.util.Objects;

import com.customerconnect.enumholder.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToOne;

@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    @Column(nullable = false)
    private String username;
 
    @Column(nullable = false)
    private String password;
    
    @Enumerated(EnumType.STRING)
    private UserType userType;

    private boolean isActive;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Login(String username, String password, UserType userType, boolean isActive, Customer customer) {
		super();
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.isActive = isActive;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, id, isActive, password, userType, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(customer, other.customer) && id == other.id && isActive == other.isActive
				&& Objects.equals(password, other.password) && userType == other.userType
				&& Objects.equals(username, other.username);
	}
    
    
}
