package com.customerconnect.daoimplementations;

import java.time.LocalDateTime;
import java.util.List;

import com.customerconnect.dao.EMUtils;
import com.customerconnect.dao.OperatorDao;
import com.customerconnect.entity.Issue;
import com.customerconnect.entity.LoggedInUserId;
import com.customerconnect.entity.Login;
import com.customerconnect.entity.Operator;
import com.customerconnect.entity.Solution;
import com.customerconnect.enumholder.UserType;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class OperatorDaoImplement implements OperatorDao {

	@Override
	public void loginOperator(String loginEmail, String loginPassword)
			throws CannotCompleteTaskException, CannotConnectException {
		
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
	        Query query = entityManager.createQuery("SELECT o FROM Operator o WHERE email = :email");
	        query.setParameter("email", loginEmail);
	        @SuppressWarnings("unchecked")
			List<Operator> operators = query.getResultList();
	        if (operators.isEmpty() || !loginPassword.equals("Operator@123")) {
	            throw new CannotCompleteTaskException("Invalid Email ID or Password!");
	        }
	        
	        Operator operator = operators.get(0);
	        Login login = new Login();
	        login.setUsername(loginEmail);
	        login.setPassword(loginPassword);
	        login.setUserType(UserType.OPERATOR);
	        login.setActive(true);
	        login.setLoginDateTime(LocalDateTime.now());
	        operator.setLogin(login);
	        login.setOperator(operator);
	        entityManager.getTransaction().begin();
	        entityManager.persist(login);
	        entityManager.getTransaction().commit();
			LoggedInUserId.loggedInUserId = operator.getOperatorId();
			String operatorName = operator.getFirstName() + "  " + operator.getLastName();
			LoggedInUserId.loggedInUserName = operatorName;
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}

	}

	@Override
	public List<Issue> getIssueList() throws NoRecordFoundException, CannotConnectException {
		EntityManager entityManager = null;
		
		try {
			entityManager = EMUtils.openConnection();
			
			Operator operator = entityManager.find(Operator.class, LoggedInUserId.loggedInUserId);
			String operatorDepartmentName = operator.getDepartment().getDepartmentName();
			
			Query query = entityManager.createQuery("SELECT i FROM Issue i where issueType = :issueType");
			query.setParameter("issueType", operatorDepartmentName);
//			query.setParameter("issueStatus", "New");
//			query.setParameter("issueStatus", "Reopend");
			
			if(query.getResultList().isEmpty()) {
				throw new NoRecordFoundException("Dear Operator, There are no Issues Found as of now...!");
			}
			
			@SuppressWarnings("unchecked")
			List<Issue> issuesList = ( List<Issue>)query.getResultList();
			
			
			return issuesList;
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	@Override
	public void addIssue(int issueId) throws CannotCompleteTaskException, CannotConnectException {
		
		EntityManager entityManager = null;
		
		try {
			entityManager = EMUtils.openConnection();
			
			Operator operator = entityManager.find(Operator.class, LoggedInUserId.loggedInUserId);
			
			Issue issue = entityManager.find(Issue.class, issueId);
			
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			List<Issue> issuesList = operator.getIssues();
			issue.setIssueStatus("Opened");
			issue.setOperator(operator);
			issuesList.add(issue);
			operator.setIssues(issuesList);
			entityTransaction.commit();
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	@Override
	public void closeCustomerIssue(int issueId) throws CannotCompleteTaskException, CannotConnectException {
		// TODO Auto-generated method stub
		EntityManager entityManager = null;
		
		try {
			entityManager = EMUtils.openConnection();
			Operator operator = entityManager.find(Operator.class, LoggedInUserId.loggedInUserId);
			
			List<Issue> issues = operator.getIssues();
			boolean isPresent = false;
			for(Issue i : issues) {
				if(i.getIssueId() == issueId) {
					isPresent = true;
					break;
				}
			}
			if(!isPresent) {
				throw new CannotCompleteTaskException("Dear Operator, There is no Issue with ID " + issueId);
			}
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Issue issue = entityManager.find(Issue.class, issueId);
			
			if(issue.getSolution() == null) {
				throw new CannotCompleteTaskException("Dear Operator, Please Resolve The Issue Before Closing It...!");
			}
			
			issue.setIssueStatus("Closed");
			entityTransaction.commit();
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	@Override
	public List<Issue> viewAllIssues() throws NoRecordFoundException, CannotConnectException {
		// TODO Auto-generated method stub
		EntityManager entityManager = null;
		
		try {
			entityManager = EMUtils.openConnection();
			
			Operator operator = entityManager.find(Operator.class, LoggedInUserId.loggedInUserId);
			
			if(operator.getIssues().isEmpty()) {
				throw new NoRecordFoundException("Dear Operator, No Issues Found");
			}
		
			List<Issue> issuesList = operator.getIssues();
			return issuesList;
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	@Override
	public void solveIssue(int issueId, String solutionDescription) throws CannotCompleteTaskException, CannotConnectException {
		// TODO Auto-generated method stub
		EntityManager entityManager = null;
		
		try {
			entityManager = EMUtils.openConnection();
			
			Issue issue = entityManager.find(Issue.class, issueId);
			
			Operator operator = entityManager.find(Operator.class, LoggedInUserId.loggedInUserId);
			
			
			
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Solution solution = new Solution();
			solution.setSolutionDescription(solutionDescription);
//			solution.setOperator(operator);
			solution.setSolutionDateTime(LocalDateTime.now());
			solution.setIssue(issue);
			issue.setSolution(solution);
			issue.setIssueStatus("Resolved");
			solution.setIssue(issue);
			
			entityTransaction.commit();
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}		
	}

}
