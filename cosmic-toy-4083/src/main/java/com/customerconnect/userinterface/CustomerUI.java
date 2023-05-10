package com.customerconnect.userinterface;

import java.util.List;
import java.util.Scanner;

import com.customerconnect.entity.Customer;
import com.customerconnect.entity.Issue;
import com.customerconnect.entity.LoggedInUserId;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;
import com.customerconnect.serviceimplementations.CustomerServiceImplement;
import com.customerconnect.services.CustomerService;

public class CustomerUI {
//	Contains all the customer functionalities to display
	static void displayCustomerFunctionalities() {
		System.out.println();
		System.out.println("1. Create Issue");
		System.out.println("2. View Issue By Id");
		System.out.println("3. Reopen Issue");
		System.out.println("4. View All Issues");
		System.out.println("5. Change Password");
		System.out.println("6. View Profile");
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
	
	
//	Creates An Issue 
	private static void createIssues(Scanner sc)  throws CannotConnectException{
		System.out.println("1. Technical Issue");
		System.out.println("2. Product Issue");
		System.out.println("3. Billing And Payment Issue");
		System.out.println("4. General Related Query");
		System.out.print("Please select the issue type: ");
		int issueType = sc.nextInt();
		String iType = null;
		switch (issueType) {
			case 1:
				iType = "Technical Issue";
				break;
			case 2:
				iType = "Product Issue";
				break;
			case 3:
				iType = "Billing And Payment Issue";
				break;
			case 4:
				iType = "General Related Query";
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + iType);
		}
		sc.nextLine();
		System.out.println("To better assist you, could you please briefly describe the reason for your call in one sentence?(200 characters only");
		String issueDescription = sc.nextLine();
		
		if(issueDescription.length() > 200) {
			System.out.println();
			System.out.println("Please describe your issue in a brief sentence of no more than 200 characters");
			return;
		}
		
		Issue issue = new Issue();
		issue.setIssueType(iType);
		issue.setIssueDescription(issueDescription);
		issue.setIssueStatus("New");
		
		try {
			CustomerService customerService = new CustomerServiceImplement();
			customerService.createIssue(issue);
			System.out.println("Issue Created Successfully..!");
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
		
	}
	
//	View Issue By ID
	private static void viewIssueById(Scanner sc) throws CannotConnectException {
		System.out.print("Please enter issue id: ");
		int issueId = sc.nextInt();
		
		try {
			CustomerService customerService = new CustomerServiceImplement();
			Issue issue = customerService.viewIssueById(issueId);
			System.out.println(issue.getIssueType());
		}catch(NoRecordFoundException noRecordFoundException) {
			System.out.println(noRecordFoundException.getMessage());
		}
		
	}
	
//	View All Issues Of Customer
	private static void viewAllIssues() throws CannotConnectException {
		try {
			CustomerService customerService = new CustomerServiceImplement();
			List<Issue> issueList = customerService.viewAllIssues();
			for(Issue issues: issueList) {
				System.out.println(issues.getIssueId() + " " + issues.getIssueStatus());
			}
		}catch(NoRecordFoundException noRecordFoundException) {
			System.out.println(noRecordFoundException.getMessage());
		}
	
	}
	
//	View Customer Profile
	private static void viewProfile() throws CannotConnectException {
		try {
			CustomerService customerService = new CustomerServiceImplement();
			Customer customer = customerService.viewProfile();
			System.out.println("My Profile: ");
			System.out.println("Profile ID: " + customer.getCustomerId());
			System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());
			System.out.println("Email Address: " + customer.getEmail());
			System.out.println("Password: " + customer.getPassword());
			System.out.println("Mobile Number: " + customer.getMobileNumber());
		}catch(CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}
	
//	Change Password
	private static void changePassword(Scanner sc) throws CannotConnectException {
		System.out.print("Please enter the current password: ");
		String currentPassword = sc.next();
		CustomerService customerService = new CustomerServiceImplement();
		try {
			customerService.checkCurrentPassword(currentPassword);
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
		System.out.print("Please enter the new password: ");
		String newPassword = sc.next();
		System.out.print("Please confirm your new password: ");
		String confirmNewPassword = sc.next();
		
		if(newPassword.contains(" ") || (newPassword.length() < 8)) {
			System.out.println("Password Criteria Not Matched..!, Please Try again");
			return;
		}else if(!(newPassword.equals(confirmNewPassword))){
			System.out.println("Password Not Matched..!");
			return;
		}
		
		try {
			customerService.updatePassword(newPassword);
			System.out.println("Password Updated Successfully...!");
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
		
	}
	
//	Customer Fucntionalities
	private static void customerFunctionalities(Scanner sc) throws CannotConnectException {
		int customerChoice = 0;
		
		do {
			displayCustomerFunctionalities();
			System.out.print("Please select one of the following preferences: ");
			customerChoice = sc.nextInt();
			switch (customerChoice) {
				case 1:
					createIssues(sc);
					break;
				case 2:
					viewIssueById(sc);
				case 3:
//					reOpenIssue(sc);
					break;
				case 4:
					viewAllIssues();
					break;
				case 5:
					changePassword(sc);
					break;
				case 6:
					viewProfile();
					break;
				case 0:
					LoggedInUserId.loggedInUserId = -1;
					LoggedInUserId.loggedInUserName = null;
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
			App.printWelcomeMessage(LoggedInUserId.loggedInUserName);
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

