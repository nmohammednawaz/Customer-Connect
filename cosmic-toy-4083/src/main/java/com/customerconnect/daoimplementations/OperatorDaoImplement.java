package com.customerconnect.daoimplementations;

import java.util.List;

import com.customerconnect.dao.EMUtils;
import com.customerconnect.dao.OperatorDao;
import com.customerconnect.entity.Operator;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class OperatorDaoImplement implements OperatorDao{

	@Override
	public void addOperator(Operator operator) throws CannotCompleteTaskException, CannotConnectException {
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
			
			Query query1 = entityManager.createQuery("SELECT COUNT(o) FROM Operator o WHERE email = :email");
			query1.setParameter("email", operator.getEmail());
			if((Long)query1.getSingleResult() > 0) {
				throw new CannotCompleteTaskException("Dear Admin, Operator with email " + operator.getEmail() + " already exists");
			}
			Query query2 = entityManager.createQuery("SELECT COUNT(o) FROM Operator o WHERE mobileNumber = :mobileNumber");
			query2.setParameter("mobileNumber", operator.getMobileNumber());
			if((Long)query2.getSingleResult() > 0) {
				throw new CannotCompleteTaskException("Dear Admin, Operator with Mobile Number " + operator.getMobileNumber() + " already exists");
			}
			
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			
			entityManager.persist(operator);
			
			entityTransaction.commit();
	
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	@Override
	public void removeOperator(int operatortId) throws CannotCompleteTaskException, CannotConnectException {
		EntityManager entityManager = null;
		
		try {
			entityManager = EMUtils.openConnection();
			Operator operator =  entityManager.find(Operator.class, operatortId);
			if(operator == null) {
				throw new CannotCompleteTaskException("Dear Admin, There is no Operator with Id " + operatortId + ", Please try with correct operator Id");
			}
			
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(operator);
			entityTransaction.commit();
			
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
		
	}

	@Override
	public Operator findOperatorById(int operatorId) throws CannotCompleteTaskException, CannotConnectException {
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
			Operator operator =  entityManager.find(Operator.class, operatorId);
			if(operator == null) {
				throw new CannotCompleteTaskException("Dear Admin, There is no operator with Id " + operatorId + ", Please try with correct operator Id");
			}
			return operator;
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	@Override
	public void modifyOperator(Operator operator) throws CannotCompleteTaskException, CannotConnectException {
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
			Operator fetchOperator =  entityManager.find(Operator.class, operator.getOperatorID());
			if(!fetchOperator.getEmail().equals(operator.getEmail())) {
				Query query1 = entityManager.createQuery("SELECT COUNT(o) FROM Operator o WHERE email = :email");
				query1.setParameter("email", operator.getEmail());
				if((Long)query1.getSingleResult() > 0) {
					throw new CannotCompleteTaskException("Dear Admin, Operator with email " + operator.getEmail() + " already exists");
				}
			}
			if(!fetchOperator.getMobileNumber().equals(operator.getMobileNumber())) {
				Query query2 = entityManager.createQuery("SELECT COUNT(o) FROM Operator o WHERE mobileNumber = :mobileNumber");
				query2.setParameter("mobileNumber", operator.getMobileNumber());
				if((Long)query2.getSingleResult() > 0) {
					throw new CannotCompleteTaskException("Dear Admin, Operator with Mobile Number " + operator.getMobileNumber() + " already exists");
				}
			}
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			
			fetchOperator.setFirstName(operator.getFirstName());
			fetchOperator.setLastName(operator.getLastName());
			fetchOperator.setEmail(operator.getEmail());
			fetchOperator.setMobileNumber(operator.getMobileNumber());
			fetchOperator.setCity(operator.getCity());
			fetchOperator.setDepartment(operator.getDepartment());
			
			entityTransaction.commit();
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Operator> viewAllOperators() throws CannotConnectException, NoRecordFoundException {
		EntityManager entityManager = null;
		List<Operator> operatorsList = null;
		try {
			entityManager = EMUtils.openConnection();
			Query fetchAllOperatorsQuery = entityManager.createQuery("SELECT o FROM Operator o");
			operatorsList = (List<Operator>) fetchAllOperatorsQuery.getResultList();
			
			if(operatorsList.size() == 0) {
				throw new NoRecordFoundException("Dear Admin, there is no any operator added yet into the system...!");
			}
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			
			
			
			entityTransaction.commit();
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
		return operatorsList;
	}

}
