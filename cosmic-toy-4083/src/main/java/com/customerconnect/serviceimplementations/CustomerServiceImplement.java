package com.customerconnect.serviceimplementations;

import java.util.List;

import com.customerconnect.dao.CustomerDao;
import com.customerconnect.daoimplementations.CustomerDaoImplement;
import com.customerconnect.entity.Customer;
import com.customerconnect.entity.Issue;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;
import com.customerconnect.services.CustomerService;

public class CustomerServiceImplement implements CustomerService {

	@Override
	public void registerCustomer(Customer customer) throws CannotCompleteTaskException, CannotConnectException {
		CustomerDao customerDao = new CustomerDaoImplement();
		customerDao.registerCustomer(customer);
	}

	@Override
	public void loginCustomer(String loginEmail, String loginPassword) throws CannotCompleteTaskException, CannotConnectException {
		CustomerDao customerDao = new CustomerDaoImplement();
		customerDao.loginCustomer(loginEmail, loginPassword);
	}

	@Override
	public void createIssue(Issue issue) throws CannotCompleteTaskException, CannotConnectException {
		CustomerDao customerDao = new CustomerDaoImplement();
		customerDao.createIssue(issue);
	}

	@Override
	public Issue viewIssueById(int issueId) throws NoRecordFoundException, CannotConnectException {
		CustomerDao customerDao = new CustomerDaoImplement();
		Issue issue = customerDao.viewIssueById(issueId);
		return issue;
	}

	@Override
	public List<Issue> viewAllIssues() throws NoRecordFoundException, CannotConnectException {
		CustomerDao customerDao = new CustomerDaoImplement();
		List<Issue> issueList = customerDao.viewAllIssues();
		return issueList;
	}

	@Override
	public Customer viewProfile() throws CannotConnectException {
		CustomerDao customerDao = new CustomerDaoImplement();
		Customer customer = customerDao.viewProfile();
		return customer;
	}

	@Override
	public void checkCurrentPassword(String currentPassword)
			throws CannotCompleteTaskException, CannotConnectException {
		CustomerDao customerDao = new CustomerDaoImplement();
		customerDao.checkCurrentPassword(currentPassword);
		
	}

	@Override
	public void updatePassword(String newPassword) throws CannotCompleteTaskException, CannotConnectException {
		
		CustomerDao customerDao = new CustomerDaoImplement();
		customerDao.updatePassword(newPassword);
		
	}

	@Override
	public void reopenIssue(int issueId, String issueDescription)
			throws CannotCompleteTaskException, CannotConnectException {
		CustomerDao customerDao = new CustomerDaoImplement();
		customerDao.reopenIssue(issueId, issueDescription);
	}

}
