package com.customerconnect.daoimplementations;

import java.time.LocalDateTime;
import java.util.List;

import com.customerconnect.dao.CustomerDao;
import com.customerconnect.dao.EMUtils;
import com.customerconnect.entity.Customer;
import com.customerconnect.entity.Issue;
import com.customerconnect.entity.LoggedInUserId;
import com.customerconnect.entity.Login;
import com.customerconnect.enumholder.UserType;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;

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
	        Query query = entityManager.createQuery("SELECT c FROM Customer c WHERE email = :email AND customerIsActive = :customerIsActive");
	        query.setParameter("email", loginEmail);
	        query.setParameter("customerIsActive", true);
	        @SuppressWarnings("unchecked")
			List<Customer> customers = query.getResultList();
	        if (customers.isEmpty()) {
	            throw new CannotCompleteTaskException("Invalid Email ID or Password!");
	        }
	        Customer customer = customers.get(0);
	        if(!customer.getPassword().equals(loginPassword)) {
	        	throw new CannotCompleteTaskException("Invalid Email ID or Password!");
	        }
	        Login login = new Login();
	        login.setUsername(loginEmail);
	        login.setPassword(loginPassword);
	        login.setUserType(UserType.CUSTOMER);
	        login.setLoginDateTime(LocalDateTime.now());
	        login.setActive(true);
	        customer.setLogin(login);
	        login.setCustomer(customer);
	        
	        entityManager.getTransaction().begin();
	        entityManager.persist(login);
	        entityManager.getTransaction().commit();
			LoggedInUserId.loggedInUserId = customer.getCustomerId();
			String customerName = customer.getFirstName() + " " + customer.getLastName();
			LoggedInUserId.loggedInUserName = customerName;
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	@Override
	public void createIssue(Issue issue) throws CannotCompleteTaskException, CannotConnectException {
		
		EntityManager entityManager = null;
		try {
			 entityManager = EMUtils.openConnection();
		        Customer customer = entityManager.find(Customer.class, LoggedInUserId.loggedInUserId);

		        EntityTransaction entityTransaction = entityManager.getTransaction();
		        entityTransaction.begin();

		        // Set the customer for the issue
		        issue.setCustomer(customer);

		        // Add the issue to the list of issues for the customer
		        List<Issue> issueList = customer.getIssues();
		        issueList.add(issue);

		        // Update the customer entity with the new list of issues
		        customer.setIssues(issueList);
		        
		        entityManager.persist(issue);

		        entityTransaction.commit();
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	@Override
	public Issue viewIssueById(int issueId) throws NoRecordFoundException, CannotConnectException {
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
			Customer customer = entityManager.find(Customer.class, LoggedInUserId.loggedInUserId);
			List<Issue> issueList = customer.getIssues();
			Issue issue = null;
		    for (Issue i : issueList) {
		       if (i.getIssueId() == issueId) {
		          issue = i;
		          break;
		       }
		    }
//			Issue issue =  entityManager.find(Issue.class, issueId);
			if(issue == null) {
				throw new NoRecordFoundException("Dear Customer, There is no Issue with Id " + issueId + ", Please try with correct Issue Id");
			}
			
			return issue;
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}
//	Query fetchAllIssueQuery = entityManager.createQuery("SELECT i FROM Issue i where customerId = :customerId");
//	fetchAllIssueQuery.setParameter("customerId", LoggedInUserId.loggedInUserId);
//	issueList = (List<Issue>) fetchAllIssueQuery.getResultList();
//	
	@Override
	public List<Issue> viewAllIssues() throws NoRecordFoundException, CannotConnectException {
		EntityManager entityManager = null;
		List<Issue> issueList = null;
		try {
			entityManager = EMUtils.openConnection();
			Customer customer = entityManager.find(Customer.class, LoggedInUserId.loggedInUserId);
			issueList = customer.getIssues();

			if(issueList.size() == 0) {
				throw new NoRecordFoundException("Dear Customer, There are no issues created as of now...!");
			}
			
			return issueList;
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
		
	}

	@Override
	public Customer viewProfile() throws CannotConnectException {
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
			Customer customer = entityManager.find(Customer.class, LoggedInUserId.loggedInUserId);
			return customer;
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
		
	}

	@Override
	public void checkCurrentPassword(String currentPassword)
			throws CannotCompleteTaskException, CannotConnectException {
		
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
			Customer customer = entityManager.find(Customer.class, LoggedInUserId.loggedInUserId);
			if(!(customer.getPassword().equals(currentPassword))) {
				throw new CannotCompleteTaskException("Dear Customer Please Enter Your Correct Current Password And Try Again...!");
			}
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	@Override
	public void updatePassword(String newPassword) throws CannotCompleteTaskException, CannotConnectException {
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
			Customer customer = entityManager.find(Customer.class, LoggedInUserId.loggedInUserId);
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			customer.setPassword(newPassword);
			entityTransaction.commit();
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	@Override
	public void reopenIssue(int issueId, String issueDescription)
			throws CannotCompleteTaskException, CannotConnectException {
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
			Issue issue = entityManager.find(Issue.class, issueId);
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			issue.setIssueStatus("Reopened");
			issue.setIssueDescription(issueDescription);
			entityTransaction.commit();
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
		
	}

}
