package com.customerconnect.serviceimplementations;

import com.customerconnect.entity.Department;
import com.customerconnect.exception.CannotAddDepartmentException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.services.DepartmentService;
import com.customerconnect.dao.DepartmentDao;
import com.customerconnect.daoimplementations.DepartmentDaoImplements;

public class DepartmentServiceImplements implements DepartmentService{

	@Override
	public void addDepartment(Department department) throws CannotAddDepartmentException, CannotConnectException {
		DepartmentDao departmentDao = new DepartmentDaoImplements();
		departmentDao.addDepartment(department);
	}

}
