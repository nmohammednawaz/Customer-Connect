package com.customerconnect.daoimplementations;

import com.customerconnect.dao.DepartmentDao;
import com.customerconnect.dao.EMUtils;
import com.customerconnect.entity.Department;
import com.customerconnect.exception.CannotAddDepartmentException;
import com.customerconnect.exception.CannotConnectException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class DepartmentDaoImplements implements DepartmentDao {

	@Override
	public void addDepartment(Department department) throws CannotAddDepartmentException, CannotConnectException {
		// TODO Auto-generated method stub
		
		EntityManager entityManager = null;
		try {
			entityManager = EMUtils.openConnection();
			Query query = entityManager.createQuery("SELECT COUNT(d) FROM Department d WHERE departmentName = :departmentName");
			query.setParameter("departmentName", department.getDepartmentName());
			
			if((Long) query.getSingleResult() > 0){
				throw new CannotAddDepartmentException("Dear Admin, Department with name " + department.getDepartmentName() + " Already Exist\nPlease try with adding different department");
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

}
