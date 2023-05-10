package com.customerconnect.services;

import java.util.List;

import com.customerconnect.entity.Department;
import com.customerconnect.entity.Operator;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;

public interface AdminService {
	
	
//	----------------------------------- Department Related Services --------------------------------
	
	void addDepartment(Department department) throws CannotCompleteTaskException, CannotConnectException;

	void removeDepartment(int departmentId) throws CannotCompleteTaskException, CannotConnectException;

	Department findDepartmentById(int departmentId) throws CannotCompleteTaskException, CannotConnectException;

	void modifyDepartment(Department department) throws CannotCompleteTaskException, CannotConnectException;
	
	
//	------------------------------------- Operator Related Services --------------------------------
	
	void addOperator(Operator operator) throws CannotCompleteTaskException, CannotConnectException;

	void removeOperator(int operatortId) throws CannotCompleteTaskException, CannotConnectException;

	Operator findOperatorById(int operatorId) throws CannotCompleteTaskException, CannotConnectException;

	void modifyOperator(Operator operator) throws CannotCompleteTaskException, CannotConnectException;

	List<Operator> viewAllOperators() throws NoRecordFoundException, CannotConnectException;
	
}
