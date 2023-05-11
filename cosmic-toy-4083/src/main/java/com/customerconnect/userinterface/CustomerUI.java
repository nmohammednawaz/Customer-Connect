package com.customerconnect.userinterface;

import java.time.LocalDateTime;
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
	private static void customerRegister(Scanner sc) {
		sc.nextLine();
		System.out.println();
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
		
		if(!customerEmail.contains("@") && !customerEmail.contains(".")){
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
			System.out.println();
			App.printStar(185);
			App.printSpace(85);
			System.out.println("🎉🎉🎉 Hurray! 🎉🎉🎉");
			App.printSpace(76);
			System.out.println("You are Successfully Registered...!");
			App.printStar(185);
			System.out.println();
			System.out.println("Dear Customer, Please Login With Your Credentials");
			customerLogin(sc);
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}
	
	
//	Creates An Issue 
	private static void createIssues(Scanner sc){
		System.out.println();
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
		System.out.println("To better assist you, could you please briefly describe the reason for your call in one sentence?(250 characters only)");
		String issueDescription = sc.nextLine();
		
		if(issueDescription.length() > 250) {
			System.out.println();
			System.out.println("Please describe your issue in a brief sentence of no more than 200 characters");
			return;
		}
		
		Issue issue = new Issue();
		issue.setIssueType(iType);
		issue.setIssueDescription(issueDescription);
		issue.setIssueStatus("New");
		issue.setIssueDateTime(LocalDateTime.now());
		
		try {
			CustomerService customerService = new CustomerServiceImplement();
			customerService.createIssue(issue);
			System.out.println();
			App.printStar(185);
			App.printSpace(80);
			System.out.println("Issue Created Successfully..!");
			App.printStar(185);
			System.out.println();
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
		
	}
	
//	View Issue By ID
	private static void viewIssueById(Scanner sc)  {
		System.out.print("Please enter issue id: ");
		int issueId = sc.nextInt();
		
		try {
			CustomerService customerService = new CustomerServiceImplement();
			Issue issue = customerService.viewIssueById(issueId);
			System.out.println();
			System.out.println("Issue Id: " + issue.getIssueId() + "\nIssue Description: " + issue.getIssueDescription() + "\nIssue Type: " + issue.getIssueType());
			System.out.println("Issue Created On: " + issue.getIssueDateTime());
			System.out.println("Issue Status: " + issue.getIssueStatus());
			if(!issue.getIssueStatus().equals("New")) {
				System.out.println("Issue Assigned to: " + issue.getOperator().getFirstName() + " " + issue.getOperator().getLastName());
			}else {
				System.err.println("Issue Assigned to: Dear Customer, Your issue will be assigned to our executive soon...!");
			}
			if(issue.getSolution() == null) {
				System.out.println("Solution: Pending...");
			}else {
				System.out.println("Solution: " + issue.getSolution().getSolutionDescription());
			}
			System.out.println();
		}catch(NoRecordFoundException noRecordFoundException) {
			System.out.println(noRecordFoundException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
		
	}
	
//	View All Issues Of Customer
	private static void viewAllIssues() {
		System.out.println();
		try {
			CustomerService customerService = new CustomerServiceImplement();
			List<Issue> issueList = customerService.viewAllIssues();
			for(Issue issues: issueList) {
				System.out.println("Issue Id: " + issues.getIssueId() + "\nIssue Description: " + issues.getIssueDescription() + "\nIssue Type: " + issues.getIssueType());
				System.out.println("Issue Created On: " + issues.getIssueDateTime());
				System.out.println("Issue Status: " + issues.getIssueStatus());
				if(!issues.getIssueStatus().equals("New")) {
					System.out.println("Issue Assigned to: " + issues.getOperator().getFirstName() + " " + issues.getOperator().getLastName());
				}else {
					System.err.println("Issue Assigned to: Pending...");
				}
				if(issues.getSolution() == null) {
					System.out.println("Solution: Pending...");
				}else {
					System.out.println("Solution: " + issues.getSolution().getSolutionDescription());
				}
				System.out.println();
			}
			App.printStar(185);
		}catch(NoRecordFoundException noRecordFoundException) {
			System.out.println(noRecordFoundException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	
	}
	
//	View Customer Profile
	private static void viewProfile(){
		try {
			CustomerService customerService = new CustomerServiceImplement();
			Customer customer = customerService.viewProfile();
			System.out.println();
			System.out.println("My Profile: ");
			System.out.println();
			System.out.println("Id: " + customer.getCustomerId());
			System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());
			System.out.println("Email Address: " + customer.getEmail());
			System.out.println("Password: " + customer.getPassword());
			System.out.println("Mobile Number: " + customer.getMobileNumber());
			System.out.println("City: " + customer.getCity());
			System.out.println();
			App.printStar(185);
		}catch(CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}
	
//	Change Password
	private static void changePassword(Scanner sc){
		System.out.println();
		System.out.print("Please enter the current password: ");
		String currentPassword = sc.next();
		CustomerService customerService = new CustomerServiceImplement();
		try {
			customerService.checkCurrentPassword(currentPassword);
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
		System.out.print("Please enter the new password: ");
		String newPassword = sc.next();
		System.out.print("Please confirm your new password: ");
		String confirmNewPassword = sc.next();
		
		if(newPassword.contains(" ") || (newPassword.length() < 8)) {
			System.out.println();
			App.printStar(185);
			App.printSpace(70);
			System.out.println("Password Criteria Not Matched..!, Please Try again");
			App.printStar(185);
			System.out.println();
			return;
		}else if(!(newPassword.equals(confirmNewPassword))){
			System.out.println();
			App.printStar(185);
			App.printSpace(85);
			System.out.println("Password Not Matched..!");
			App.printStar(185);
			System.out.println();
			return;
		}
		
		try {
			customerService.updatePassword(newPassword);
			System.out.println();
			App.printStar(185);
			App.printSpace(80);
			System.out.println("Password Updated Successfully...!");
			App.printStar(185);
			System.out.println();
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
		
	}
	
//	Re Open Issue
	private static void reOpenIssue(Scanner sc) {
		System.out.println();
		System.out.print("Please enter the issue id which you want to reopen: ");
		int issueId = sc.nextInt();
		
		try {
			CustomerService customerService = new CustomerServiceImplement();
			Issue issue = customerService.viewIssueById(issueId);
			if(issue.getIssueStatus().equals("Reopned")) {
				System.out.println();
				App.printStar(185);
				App.printSpace(70);
				System.out.println("Dear Customer, Your issue is already reopned...!");
				App.printStar(185);
				System.out.println();
				return;
			}else if(issue.getIssueStatus().equals("New")) {
				System.out.println();
				App.printStar(185);
				App.printSpace(45);
				System.out.println("Dear Customer, Your issue is yet to be resolved, Please wait for the response from our team...!");
				App.printStar(185);
				System.out.println();
				return;
			}else if(issue.getIssueStatus().equals("Opened")){
				System.out.println();
				App.printStar(185);
				App.printSpace(50);
				System.out.println("Dear customer, Your issue is in progress, Please wait for the response from our team...!");
				App.printStar(185);
				System.out.println();
				return;
			}
			sc.nextLine();
			System.out.println("To better assist you, could you please briefly describe the reason for your call in one sentence?(250 characters only");
			String issueDescription = sc.nextLine();
			
			if(issueDescription.length() > 250) {
				System.out.println();
				System.out.println("Please describe your issue in a brief sentence of no more than 200 characters");
				return;
			}
			customerService.reopenIssue(issueId, issueDescription);
			System.out.println();
			App.printStar(185);
			App.printSpace(82);
			System.out.println("Issue Reopned Successfully...!");
			App.printStar(185);
			System.out.println();
		}catch(NoRecordFoundException noRecordFoundException) {
			System.out.println(noRecordFoundException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		} catch (CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
		
	}
	
//	Customer Fucntionalities
	private static void customerFunctionalities(Scanner sc){
		int customerChoice = 0;
		
		do {
			displayCustomerFunctionalities();
			System.out.println();
			System.out.print("Please select one of the following preferences: ");
			customerChoice = sc.nextInt();
			switch (customerChoice) {
				case 1:
					createIssues(sc);
					break;
				case 2:
					viewIssueById(sc);
				case 3:
					reOpenIssue(sc);
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
					System.out.println();
					App.printStar(185);
					App.printSpace(90);
					System.out.println("Logout Successfull..!");
					App.printStar(185);
					System.out.println();
					break;
				default:
					System.out.println();
					App.printStar(185);
					App.printSpace(68);
					System.out.println("🚫Please enter the correct preference and try again..!");
					App.printStar(185);
					System.out.println();
					break;
			}
		}while(customerChoice != 0);
		
	}


	//	Customer Login
	private static void customerLogin(Scanner sc) {
		System.out.println();
		System.out.print("Please enter your email address: ");
		String loginEmail = sc.next();
		System.out.print("Please enter your password: ");
		String loginPassword = sc.next();
		
		try {
			CustomerService customerService = new CustomerServiceImplement();
			customerService.loginCustomer(loginEmail, loginPassword);
			System.out.println();
			App.printStar(185);
			App.printSpace(80);
			App.printWelcomeMessage(LoggedInUserId.loggedInUserName.toUpperCase());
			App.printStar(185);
			System.out.println();
			customerFunctionalities(sc);
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
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
			System.out.println();
			System.out.print("Please enter your preference: ");
			customerchoice = sc.nextInt();
			
			switch(customerchoice) {
				case 1:
					customerLogin(sc);
					break;
				case 2:
					customerRegister(sc);
					System.out.println();
					break;
				case 0:
					System.out.println("Redirected To Main");
					App.main(null);
					break;
				default:
					System.out.println();
					App.printStar(185);
					App.printSpace(68);
					System.out.println("🚫Please enter the correct preference and try again..!");
					App.printStar(185);
					System.out.println();
					break;
			}
			System.out.println();
		}while(customerchoice != 0);
		
	}
	
}

