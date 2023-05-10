package com.customerconnect.daoimplementations;

import java.util.List;

import com.customerconnect.dao.AdminDao;
import com.customerconnect.dao.EMUtils;
import com.customerconnect.entity.Department;
import com.customerconnect.entity.Operator;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class AdminDaoImplement implements AdminDao{
	
//	----------------------------------- Department Dao Implement ----------------------------------------
	
	@Override
	public void addDepartment(Department department) throws CannotCompleteTaskException, CannotConnectException {
		// TODO Auto-generated method stub
		
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
			Query query = entityManager.createQuery("SELECT COUNT(d) FROM Department d WHERE departmentName = :departmentName");
			query.setParameter("departmentName", department.getDepartmentName());
			
			if((Long) query.getSingleResult() > 0){
				throw new CannotCompleteTaskException("Dear Admin, Department with name " + department.getDepartmentName() + " Already Exist\nPlease try with adding different department");
			}
			
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.persist(department);
			entityTransaction.commit();
	
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
				entityManager.close();
		}
	}

	@Override
	public void removeDepartment(int departmentId) throws CannotCompleteTaskException, CannotConnectException {
		// TODO Auto-generated method stub
		EntityManager entityManager = null;
		
		try {
			entityManager = EMUtils.openConnection();
			Department department =  entityManager.find(Department.class, departmentId);
			if(department == null) {
				throw new CannotCompleteTaskException("Dear Admin, There is no Department with Id " + departmentId + ", Please try with correct department Id");
			}
			
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			entityManager.remove(department);
			entityTransaction.commit();
			
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	@Override
	public Department findDepartmentById(int departmentId) throws CannotCompleteTaskException, CannotConnectException {
		// TODO Auto-generated method stub
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
			Department department =  entityManager.find(Department.class, departmentId);
			if(department == null) {
				throw new CannotCompleteTaskException("Dear Admin, There is no Department with Id " + departmentId + ", Please try with correct department Id");
			}
			
			return department;
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	@Override
	public void modifyDepartment(Department department) throws CannotCompleteTaskException, CannotConnectException {
		// TODO Auto-generated method stub
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
			Department fetchDepartment =  entityManager.find(Department.class, department.getDepartmentId());
			if(fetchDepartment.getDepartmentName().equals(department.getDepartmentName())) {
				throw new CannotCompleteTaskException("Dear Admin, Departent Name With " + department.getDepartmentName() + " Already Exists, Please Try With Different Name");
			}
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			fetchDepartment.setDepartmentName(department.getDepartmentName());
			entityTransaction.commit();
			
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
	}

	

//	------------------------------------ Operator Dao Implement ------------------------------------------
	
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
			Department fetchDepartment = entityManager.find(Department.class, operator.getDepartment().getDepartmentId());
			
			operator.setDepartment(fetchDepartment);
			List<Operator> operatorsSet = fetchDepartment.getOperators();
			operatorsSet.add(operator);
			
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
			Operator fetchOperator =  entityManager.find(Operator.class, operator.getOperatorId());
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
			
			return operatorsList;
		}catch(PersistenceException ex) {
			throw new CannotConnectException("Unable to Connect, Please try again");
		}finally {
			entityManager.close();
		}
		
	}

	
	
}
