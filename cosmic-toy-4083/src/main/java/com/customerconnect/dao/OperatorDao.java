package com.customerconnect.dao;

import java.util.List;

import com.customerconnect.entity.Issue;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;

public interface OperatorDao {
	
	void loginOperator(String loginEmail, String loginPassword) throws CannotCompleteTaskException, CannotConnectException;

	List<Issue> getIssueList() throws NoRecordFoundException, CannotConnectException;

	void addIssue(int issueId) throws CannotCompleteTaskException, CannotConnectException;

	void closeCustomerIssue(int issueId) throws CannotCompleteTaskException, CannotConnectException;

	List<Issue> viewAllIssues() throws NoRecordFoundException, CannotConnectException;

	void solveIssue(int issueId, String solutionDescription) throws CannotCompleteTaskException, CannotConnectException;

}
