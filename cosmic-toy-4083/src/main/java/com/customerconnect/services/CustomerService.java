package com.customerconnect.services;

import com.customerconnect.entity.Customer;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;

public interface CustomerService {
	void registerCustomer(Customer customer) throws CannotCompleteTaskException, CannotConnectException;

	void loginCustomer(String loginEmail, String loginPassword) throws CannotCompleteTaskException, CannotConnectException;
}
