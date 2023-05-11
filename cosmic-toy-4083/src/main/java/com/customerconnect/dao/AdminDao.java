package com.customerconnect.dao;

import java.util.List;

import com.customerconnect.entity.Customer;
import com.customerconnect.entity.Department;
import com.customerconnect.entity.Login;
import com.customerconnect.entity.Operator;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;

public interface AdminDao {
	
//	----------------------------------- Department Dao Interfaces ----------------------------------------
	
	void addDepartment(Department department) throws CannotCompleteTaskException, CannotConnectException;

	void removeDepartment(int departmentId) throws CannotCompleteTaskException, CannotConnectException;
	
	Department findDepartmentById(int departmentId) throws CannotCompleteTaskException, CannotConnectException;

	void modifyDepartment(Department department) throws CannotCompleteTaskException, CannotConnectException;
	
	
//	----------------------------------- Operator Dao Interfaces ----------------------------------------
	
	
	void addOperator(Operator operator) throws CannotCompleteTaskException, CannotConnectException;

	void removeOperator(int operatortId) throws CannotCompleteTaskException, CannotConnectException;

	Operator findOperatorById(int operatorId) throws CannotCompleteTaskException, CannotConnectException;

	void modifyOperator(Operator operator) throws CannotCompleteTaskException, CannotConnectException;

	List<Operator> viewAllOperators() throws NoRecordFoundException, CannotConnectException;

//	----------------------------------- Customer Dao Interfaces ----------------------------------------
	
	
	List<Customer> viewAllCustomers() throws NoRecordFoundException, CannotConnectException;

	Customer findCustomerById(int customerId) throws NoRecordFoundException, CannotConnectException;

	Customer findCustomerByEmail(String customerEmail) throws NoRecordFoundException, CannotConnectException;

	List<Customer> findCustomersByName(String customerFirstName, String customerLastName) throws NoRecordFoundException, CannotConnectException;

	void blockCustomer(int customerId) throws NoRecordFoundException, CannotConnectException, CannotCompleteTaskException;

	List<Login> getLoginDetails() throws NoRecordFoundException, CannotConnectException;;

	
}
