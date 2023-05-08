package com.customerconnect.userinterface;

import java.util.Scanner;

import com.customerconnect.entity.Customer;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.serviceimplementations.CustomerServiceImplement;
import com.customerconnect.services.CustomerService;

public class CustomerUI {
//	Contains all the customer functionalities to display
	static void displayCustomerFunctionalities() {
		System.out.println();
		System.out.println("1. View Issue By Id");
		System.out.println("2. Reopen Issue");
		System.out.println("3. View All Issues");
		System.out.println("4. Change Password");
		System.out.println("5. View Profile");
		System.out.println("0. Logout");
		System.out.println();
	}
	
	
//	Customer Registeration
	private static void customerRegister(Scanner sc) throws CannotConnectException {
		sc.nextLine();
		System.out.print("Please enter your first name: ");
		String customerFirstName = sc.nextLine();
		System.out.print("Please enter your last name: ");
		String customerLastName = sc.nextLine();
		System.out.print("Please enter your email address: ");
		String customerEmail = sc.next();
		System.out.print("Please enter your password(Must have Minimum Length of 8 and not include any space): ");
		String password = sc.next();
		System.out.print("Please confirm password: ");
		String confirmPassword = sc.next();
		System.out.print("Please enter your mobile number: ");
		String customerMobile = sc.next();
		System.out.print("Please enter your city: ");
		String customerCity = sc.next();
		boolean customerIsActive = true;
		
		if(!customerEmail.contains("@") || !customerEmail.contains(".")){
			System.out.println("Please Enter Correct Email Adress And Try Again");
			return;
		}else if(password.contains(" ") || (password.length() < 8)) {
			System.out.println("Password Criteria Not Matched..!, Please Try again");
			return;
		}else if(!(password.equals(confirmPassword))){
			System.out.println("Password Not Matched..!");
			return;
		}
		
		Customer customer = new Customer();
		customer.setFirstName(customerFirstName);
		customer.setLastName(customerLastName);
		customer.setEmail(customerEmail);
		customer.setPassword(password);
		customer.setMobileNumber(customerMobile);
		customer.setCity(customerCity);
		customer.setCustomerIsActive(customerIsActive);
		
		try {
			CustomerService customerService = new CustomerServiceImplement();
			customerService.registerCustomer(customer);
			System.out.println("🎉🎉🎉 Hurray! 🎉🎉🎉");
			System.out.println("You are Successfully Registered...!");
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
	}
	
//	Customer Fucntionalities
	private static void customerFunctionalities(Scanner sc) {
		int customerChoice = 0;
		
		do {
			displayCustomerFunctionalities();
			System.out.print("Please select one of the following preferences: ");
			customerChoice = sc.nextInt();
			switch (customerChoice) {
				case 1:
//					viewIssuesById(sc);
					break;
				case 2:
//					reOpenIssue(sc);
					break;
				case 3:
//					viewAllIssues();
					break;
				case 4:
//					changePassword(sc);
					break;
				case 5:
//					viewProfile(sc);
					break;
				case 0:
					System.out.println("Logout Successfull..!");
					break;
				default:
					System.out.println("🚫Please enter the correct preference and try again..!");
					break;
			}
		}while(customerChoice != 0);
		
	}

//	Customer Login
	private static void customerLogin(Scanner sc) throws CannotConnectException {
		System.out.print("Please enter your email address: ");
		String loginEmail = sc.next();
		System.out.print("Please enter your password: ");
		String loginPassword = sc.next();
		
		try {
			CustomerService customerService = new CustomerServiceImplement();
			customerService.loginCustomer(loginEmail, loginPassword);
			App.printWelcomeMessage("Customer");
			customerFunctionalities(sc);
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
		
	}
	


	//	Ask customer for login or register
	static void userIsCustomer(Scanner sc) throws CannotConnectException {
		
		int customerchoice = 0;
		do {
			System.out.println();
			System.out.println("Press 1 if you are an existing customer");
			System.out.println("Press 2 if you are a new user");
			System.out.println("Press 0 to go for main");
			System.out.print("Please enter your preference: ");
			customerchoice = sc.nextInt();
			
			switch(customerchoice) {
				case 1:
					customerLogin(sc);
					break;
				case 2:
					customerRegister(sc);
					System.out.println();
					System.out.println("Dear Customer, Please Login With Your Credentials");
					customerLogin(sc);
					break;
				case 0:
					System.out.println("Redirected To Main");
					App.main(null);
					break;
				default:
					System.out.println("🚫Please enter the correct preference and try again..!");
					break;
			}
			System.out.println();
		}while(customerchoice != 0);
		
	}
	
}

