package com.customerconnect.serviceimplementations;

import com.customerconnect.dao.CustomerDao;
import com.customerconnect.daoimplementations.CustomerDaoImplement;
import com.customerconnect.entity.Customer;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.services.CustomerService;

public class CustomerServiceImplement implements CustomerService {

	@Override
	public void registerCustomer(Customer customer) throws CannotCompleteTaskException, CannotConnectException {
		CustomerDao customerDao = new CustomerDaoImplement();
		customerDao.registerCustomer(customer);
	}

	@Override
	public void loginCustomer(String loginEmail, String loginPassword) throws CannotCompleteTaskException, CannotConnectException {
		CustomerDao customerDao = new CustomerDaoImplement();
		customerDao.loginCustomer(loginEmail, loginPassword);
	}

}
