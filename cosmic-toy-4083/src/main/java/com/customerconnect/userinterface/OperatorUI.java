package com.customerconnect.userinterface;

import java.util.List;
import java.util.Scanner;
import com.customerconnect.entity.Issue;
import com.customerconnect.entity.LoggedInUserId;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;
import com.customerconnect.serviceimplementations.OperatorServiceImplement;
import com.customerconnect.services.OperatorService;

public class OperatorUI {
	
//	Displays Operator Functionalities
	private static void displayOperatorFunctionalities() {
		System.out.println();
		System.out.println("1. View All Issues");
		System.out.println("2. Add Customer Issue");
		System.out.println("3. Solve Customer Issue");
		System.out.println("4. Close Customer Issue");
		System.out.println("0. Logout");
		System.out.println();
	}
	
	
//	Add Customer Issue 
	private static void addCustomerIssue(Scanner sc) {
		List<Issue> issuesList = null;
		OperatorService operatorService = new OperatorServiceImplement();
		try {
			System.out.println();
			System.out.println("List of Issues:");
			System.out.println();
			issuesList = operatorService.getIssueList();
			for(Issue issues: issuesList) {
				System.out.println("\nCustomer Id: " + issues.getCustomer().getCustomerId() +
						"\nIssue Id: " +issues.getIssueId() + 
						"\nIssue Type: " + issues.getIssueType() + 
						"\nIssue Status: " + issues.getIssueStatus() + 
						"\nIssue Description: " + issues.getIssueDescription() +
						"\n"
				);
			}
		}catch(NoRecordFoundException noRecordFoundException) {
			System.out.println(noRecordFoundException.getMessage());
		}catch(CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
		System.out.print("please enter issue Id to be added: ");
		int issueId = sc.nextInt();
		boolean isPresent = false;
		for(Issue issues: issuesList) {
			if(issues.getIssueId() == issueId) {
				isPresent = true;
				break;
			}
		}
		if(!isPresent) {
			System.out.println("Dear Operator, There is no Issue with Id " + issueId + " present, Please try with correct issue Id");
			return;
		}
		try {
			operatorService.addIssue(issueId);
			System.out.println();
			App.printStar(185);
			App.printSpace(80);
			System.out.println("Issue Added Successfully...!");
			App.printStar(185);
			System.out.println();
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}catch(CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}
	
//	Close Customer Issue
	private static void closeCustomerIssue(Scanner sc) {
		System.out.println();
		System.out.println("Please enter issue id to be closed");
		int issueId = sc.nextInt();
		try {
			OperatorService operatorService = new OperatorServiceImplement();
			operatorService.closeCustomerIssue(issueId);
			System.out.println();
			App.printStar(185);
			App.printSpace(80);
			System.out.println("Issue Closed Successfully...!");
			App.printStar(185);
			System.out.println();
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}  catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}
	
//	View All Issues
	private static void viewAllIssues() {
		List<Issue> issuesList = null;
		OperatorService operatorService = new OperatorServiceImplement();
		try {
			System.out.println("List of Issues:");
			issuesList = operatorService.viewAllIssues();
			for(Issue issues: issuesList) {
				System.out.println("\nCustomer Id: " + issues.getCustomer().getCustomerId() +
						"\nIssue Id: " +issues.getIssueId() + 
						"\nIssue Type: " + issues.getIssueType() + 
						"\nIssue Status: " + issues.getIssueStatus() + 
						"\nIssue Description: " + issues.getIssueDescription()
				);
				if(issues.getSolution() == null) {
					System.out.println("Solution: Not Solved Yet");
				}else {
					System.out.println("Solution: " + issues.getSolution().getSolutionDescription());
				}
				System.out.println();
			}
			
		}catch(NoRecordFoundException noRecordFoundException) {
			System.out.println(noRecordFoundException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
		
	}
	
//	Solve Customer Issue
	private static void solveCustomerIssue(Scanner sc) {
		List<Issue> issuesList = null;
		OperatorService operatorService = new OperatorServiceImplement();
		try {
			System.out.println("List of Issue:");
			System.out.println();
			issuesList = operatorService.getIssueList();
			
			for(Issue issues: issuesList) {
				System.out.println("Customer Id: " + issues.getCustomer().getCustomerId() +
						"\nIssue Id: " +issues.getIssueId() + 
						"\nIssue Type: " + issues.getIssueType() + 
						"\nIssue Status: " + issues.getIssueStatus() + 
						"\nIssue Description: " + issues.getIssueDescription() + 
						"\n"
				);
			}
			
		}catch(NoRecordFoundException noRecordFoundException) {
			System.out.println(noRecordFoundException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
		
		System.out.print("please enter issue Id to resolve: ");
		int issueId = sc.nextInt();
		boolean isPresent = false;
		for(Issue issues: issuesList) {
			if(issues.getIssueId() == issueId) {
				isPresent = true;
				if(issues.getSolution() != null) {
					System.out.println("Dear Operator, Issue with Id " + issueId + " Is Already Resolved");
					return;
				}
				break;
			}
		}
		if(!isPresent) {
			System.out.println("Dear Operator, There is no Issue with Id " + issueId + " present, Please try with correct issue Id");
			return;
		}
		sc.nextLine();
		System.out.println("Please describe the solution within 250 characters: ");
		String solutionDescription = sc.nextLine();
		
		if(solutionDescription.length() > 250) {
			System.out.println("Dear Operator, describe issue within 250 characters and try again...!");
			return;
		}
		try {
			operatorService.solveIssue(issueId, solutionDescription);
			System.out.println("Issue Resolved Successfully...!");
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}catch(CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}
	
//	Operator Functionalities
private static void operatorFunctionalities(Scanner sc) {
	System.out.println();
		int operatorChoice = 0;
		do {
			displayOperatorFunctionalities();
			System.out.print("Please select one of the following preferences: ");
			operatorChoice = sc.nextInt();
			switch (operatorChoice) {
				case 1:
					viewAllIssues();
					break;
				case 2:
					addCustomerIssue(sc);
					break;
				case 3:
					solveCustomerIssue(sc);
					break;
				case 4:
					closeCustomerIssue(sc);
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
		}while(operatorChoice != 0);		
	}



	//	Operator Login
	public static void operatorLogin(Scanner sc) {
		System.out.println();
		System.out.println("Dear Operator, Please login with your credentials...!");
		System.out.println();
		System.out.print("Please enter your email address: ");
		String loginEmail = sc.next();
		System.out.print("Please enter your password: ");
		String loginPassword = sc.next();
		
		try {
			OperatorService operatorService = new OperatorServiceImplement();
			operatorService.loginOperator(loginEmail, loginPassword);
			System.out.println();
			App.printStar(185);
			App.printSpace(80);
			App.printWelcomeMessage(LoggedInUserId.loggedInUserName.toUpperCase());
			App.printStar(185);
			System.out.println();
			operatorFunctionalities(sc);
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}catch(CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}

	
}
