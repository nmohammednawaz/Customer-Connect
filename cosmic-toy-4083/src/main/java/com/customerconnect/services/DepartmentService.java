package com.customerconnect.services;

import com.customerconnect.entity.Department;
import com.customerconnect.exception.CannotAddDepartmentException;
import com.customerconnect.exception.CannotConnectException;

public interface DepartmentService {
	void addDepartment(Department department) throws CannotAddDepartmentException, CannotConnectException;
}
