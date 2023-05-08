package com.customerconnect.daoimplementations;

import java.util.List;

import com.customerconnect.dao.CustomerDao;
import com.customerconnect.dao.EMUtils;
import com.customerconnect.entity.Customer;
import com.customerconnect.entity.Login;
import com.customerconnect.enumholder.UserType;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CustomerDaoImplement implements CustomerDao {

	@Override
	public void registerCustomer(Customer customer) throws CannotCompleteTaskException, CannotConnectException {
		
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
			Query query = entityManager.createQuery("SELECT COUNT(c) FROM Customer c WHERE email = :email");
			query.setParameter("email", customer.getEmail());
			
			if((Long) query.getSingleResult() > 0){
				throw new CannotCompleteTaskException("Dear Customer, Your Acoount Already Exists With Email: " + customer.getEmail());
			}
			
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.persist(customer);
			entityTransaction.commit();
	
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	@Override
	public void loginCustomer(String loginEmail, String loginPassword)
			throws CannotCompleteTaskException, CannotConnectException {
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
	        Query query = entityManager.createQuery("SELECT c FROM Customer c WHERE email = :email AND password = :password AND customerIsActive = :customerIsActive");
	        query.setParameter("email", loginEmail);
	        query.setParameter("password", loginPassword);
	        query.setParameter("customerIsActive", true);
	        @SuppressWarnings("unchecked")
			List<Customer> customers = query.getResultList();
	        if (customers.isEmpty()) {
	            throw new CannotCompleteTaskException("Invalid Email ID or Password!");
	        }
	        Customer customer = customers.get(0);
	        Login login = new Login();
	        login.setUsername(loginEmail);
	        login.setPassword(loginPassword);
	        login.setUserType(UserType.CUSTOMER);
	        login.setActive(true);
	        customer.setLogin(login);
	        login.setCustomer(customer);
	        entityManager.getTransaction().begin();
	        entityManager.persist(login);
	        entityManager.getTransaction().commit();
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

}
