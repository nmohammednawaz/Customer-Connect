package com.customerconnect.serviceimplementations;

import com.customerconnect.entity.Department;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.services.DepartmentService;
import com.customerconnect.dao.DepartmentDao;
import com.customerconnect.daoimplementations.DepartmentDaoImplements;

public class DepartmentServiceImplements implements DepartmentService{

	@Override
	public void addDepartment(Department department) throws CannotCompleteTaskException, CannotConnectException {
		DepartmentDao departmentDao = new DepartmentDaoImplements();
		departmentDao.addDepartment(department);
	}

	@Override
	public void removeDepartment(int departmentId) throws CannotCompleteTaskException, CannotConnectException {
		// TODO Auto-generated method stub
		DepartmentDao departmentDao = new DepartmentDaoImplements();
		departmentDao.removeDepartment(departmentId);
		
	}

	@Override
	public Department findDepartmentById(int departmentId) throws CannotCompleteTaskException, CannotConnectException {
		// TODO Auto-generated method stub
		DepartmentDao departmentDao = new DepartmentDaoImplements();
		Department department = departmentDao.findDepartmentById(departmentId);
		return department;
	}

	@Override
	public void modifyDepartment(Department department) throws CannotCompleteTaskException, CannotConnectException {
		// TODO Auto-generated method stub
		DepartmentDao departmentDao = new DepartmentDaoImplements();
		departmentDao.modifyDepartment(department);
	}

}
