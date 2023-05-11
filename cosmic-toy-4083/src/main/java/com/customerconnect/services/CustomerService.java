package com.customerconnect.services;

import java.util.List;

import com.customerconnect.entity.Customer;
import com.customerconnect.entity.Issue;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;

public interface CustomerService {
	
	void registerCustomer(Customer customer) throws CannotCompleteTaskException, CannotConnectException;

	void loginCustomer(String loginEmail, String loginPassword) throws CannotCompleteTaskException, CannotConnectException;

	void createIssue(Issue issue) throws CannotCompleteTaskException, CannotConnectException;

	Issue viewIssueById(int issueId)throws NoRecordFoundException, CannotConnectException;

	List<Issue> viewAllIssues()throws NoRecordFoundException, CannotConnectException;

	Customer viewProfile() throws CannotConnectException;

	void checkCurrentPassword(String currentPassword) throws CannotCompleteTaskException, CannotConnectException;

	void updatePassword(String newPassword) throws CannotCompleteTaskException, CannotConnectException;

	void reopenIssue(int issueId, String issueDescription) throws CannotCompleteTaskException, CannotConnectException;
}
