package com.customerconnect.daoimplementations;

import com.customerconnect.dao.DepartmentDao;
import com.customerconnect.dao.EMUtils;
import com.customerconnect.entity.Department;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class DepartmentDaoImplements implements DepartmentDao {

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
			Query query = entityManager.createQuery("DELETE FROM Department d WHERE departmentId = :departmentId");
			query.setParameter("departmentId", departmentId);
			query.executeUpdate();
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
			if(!fetchDepartment.getDepartmentName().equals(department.getDepartmentName())) {
				Query query = entityManager.createQuery("SELECT COUNT(d) FROM Department d WHERE departmentName = :departmentName");
				query.setParameter("departmentName", department.getDepartmentName());
				if((Long) query.getSingleResult() > 0) {
					throw new CannotCompleteTaskException("Dear Admin, Departent Name With " + department.getDepartmentName() + " Already Exists, Please Try With Different Name");
				}
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

}
