package com.customerconnect.dao;

import com.customerconnect.entity.Department;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;

public interface DepartmentDao {
	void addDepartment(Department department) throws CannotCompleteTaskException, CannotConnectException;

	void removeDepartment(int departmentId) throws CannotCompleteTaskException, CannotConnectException;
	
	Department findDepartmentById(int departmentId) throws CannotCompleteTaskException, CannotConnectException;

	void modifyDepartment(Department department) throws CannotCompleteTaskException, CannotConnectException;

}
