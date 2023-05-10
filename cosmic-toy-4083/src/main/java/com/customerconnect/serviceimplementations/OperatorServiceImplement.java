package com.customerconnect.serviceimplementations;

import java.util.List;

import com.customerconnect.dao.OperatorDao;
import com.customerconnect.daoimplementations.OperatorDaoImplement;
import com.customerconnect.entity.Issue;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;
import com.customerconnect.services.OperatorService;

public class OperatorServiceImplement implements OperatorService {

	@Override
	public void loginOperator(String loginEmail, String loginPassword)
			throws CannotCompleteTaskException, CannotConnectException {
		OperatorDao operatorDao = new OperatorDaoImplement();
		operatorDao.loginOperator(loginEmail, loginPassword);
	}

	@Override
	public List<Issue> getIssueList() throws NoRecordFoundException, CannotConnectException {
		// TODO Auto-generated method stub
		OperatorDao operatorDao = new OperatorDaoImplement();
		List<Issue> issuesList = operatorDao.getIssueList();
		return issuesList;
	}

	@Override
	public void addIssue(int issueId) throws CannotCompleteTaskException, CannotConnectException {
		
		OperatorDao operatorDao = new OperatorDaoImplement();
		operatorDao.addIssue(issueId);
	}

	@Override
	public void closeCustomerIssue(int issueId) throws CannotCompleteTaskException, CannotConnectException {
		// TODO Auto-generated method stub
		OperatorDao operatorDao = new OperatorDaoImplement();
		operatorDao.closeCustomerIssue(issueId);
	}

	@Override
	public List<Issue> viewAllIssues() throws NoRecordFoundException, CannotConnectException {
		// TODO Auto-generated method stub
		OperatorDao operatorDao = new OperatorDaoImplement();
		List<Issue> issuesList = operatorDao.viewAllIssues();
		return issuesList;
	}

	@Override
	public void solveIssue(int issueId, String solutionDescription) throws CannotCompleteTaskException, CannotConnectException {
		// TODO Auto-generated method stub
		OperatorDao operatorDao = new OperatorDaoImplement();
		operatorDao.solveIssue(issueId, solutionDescription);
	}


}
