package com.customerconnect.services;

import com.customerconnect.entity.Department;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;

public interface DepartmentService {
	void addDepartment(Department department) throws CannotCompleteTaskException, CannotConnectException;

	void removeDepartment(int departmentId) throws CannotCompleteTaskException, CannotConnectException;

	Department findDepartmentById(int departmentId) throws CannotCompleteTaskException, CannotConnectException;

	void modifyDepartment(Department department) throws CannotCompleteTaskException, CannotConnectException;
}
