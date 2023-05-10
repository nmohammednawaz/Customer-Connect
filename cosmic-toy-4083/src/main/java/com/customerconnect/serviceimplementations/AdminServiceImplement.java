package com.customerconnect.serviceimplementations;

import java.util.List;

import com.customerconnect.dao.AdminDao;
import com.customerconnect.daoimplementations.AdminDaoImplement;
import com.customerconnect.entity.Department;
import com.customerconnect.entity.Operator;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;
import com.customerconnect.services.AdminService;

public class AdminServiceImplement implements AdminService{
	
//	---------------------------------- Department Service Implements ----------------------------------
	@Override
	public void addDepartment(Department department) throws CannotCompleteTaskException, CannotConnectException {
		AdminDao departmentDao = new AdminDaoImplement();
		departmentDao.addDepartment(department);
	}

	@Override
	public void removeDepartment(int departmentId) throws CannotCompleteTaskException, CannotConnectException {
		// TODO Auto-generated method stub
		AdminDao departmentDao = new AdminDaoImplement();
		departmentDao.removeDepartment(departmentId);
		
	}

	@Override
	public Department findDepartmentById(int departmentId) throws CannotCompleteTaskException, CannotConnectException {
		// TODO Auto-generated method stub
		AdminDao departmentDao = new AdminDaoImplement();
		Department department = departmentDao.findDepartmentById(departmentId);
		return department;
	}

	@Override
	public void modifyDepartment(Department department) throws CannotCompleteTaskException, CannotConnectException {
		// TODO Auto-generated method stub
		AdminDao departmentDao = new AdminDaoImplement();
		departmentDao.modifyDepartment(department);
	}
	
	
//	---------------------------------- Operator Service Implements ----------------------------------
	
	@Override
	public void addOperator(Operator operator) throws CannotCompleteTaskException, CannotConnectException {
		AdminDao operatorDao = new AdminDaoImplement();
		operatorDao.addOperator(operator);

	}

	@Override
	public void removeOperator(int operatortId) throws CannotCompleteTaskException, CannotConnectException {
		AdminDao operatorDao = new AdminDaoImplement();
		operatorDao.removeOperator(operatortId);
	}

	@Override
	public Operator findOperatorById(int operatorId) throws CannotCompleteTaskException, CannotConnectException {
		AdminDao operatorDao = new AdminDaoImplement();
		Operator operator = operatorDao.findOperatorById(operatorId);
		return operator;
	}

	@Override
	public void modifyOperator(Operator operator) throws CannotCompleteTaskException, CannotConnectException {
		AdminDao operatorDao = new AdminDaoImplement();
		operatorDao.modifyOperator(operator);
	}

	@Override
	public List<Operator> viewAllOperators() throws NoRecordFoundException, CannotConnectException {
		AdminDao operatorDao = new AdminDaoImplement();
		List<Operator> operatorsList= operatorDao.viewAllOperators();
		return operatorsList;
	}
	
	
}
