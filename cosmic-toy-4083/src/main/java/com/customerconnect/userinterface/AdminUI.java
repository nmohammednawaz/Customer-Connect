package com.customerconnect.userinterface;

import java.util.Scanner;

import com.customerconnect.entity.Department;
import com.customerconnect.exception.CannotAddDepartmentException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.serviceimplementations.DepartmentServiceImplements;
import com.customerconnect.services.DepartmentService;

public class AdminUI {
	
//	Contains all the admin functionalities to display
	static void displayAdminFunctionalities() {
		System.out.println();
		System.out.println("1. Add Department");
		System.out.println("2. Remove Department");
		System.out.println("3. Modify Department");
		System.out.println("4. Find Department By ID");
		System.out.println("5. Add Operator");
		System.out.println("6. Remove Operator");
		System.out.println("7. Modify Operator");
		System.out.println("8. Find Operator By ID");
		System.out.println("9. View All Operators");
		System.out.println("0. Logout");
		System.out.println();
	}
	
	
//	Add Department
	static void addDepartment(Scanner sc) throws CannotConnectException {
		System.out.println("Please enter the department name");
		sc.nextLine();
		String departmentName = sc.nextLine();
		
		Department department = new Department(departmentName);
		DepartmentService departmentService = new DepartmentServiceImplements();
		
		try {
			departmentService.addDepartment(department);
			System.out.println("Department Added Successfully...!");
		}catch(CannotAddDepartmentException cannotAddepartmentException) {
			System.out.println(cannotAddepartmentException.getMessage());
		}
		
	}
	
	
//	Remove Departmnent
	static void removeDepartment(Scanner sc) throws CannotConnectException{
		
	}
	
//	Admin Functionalities
	static void adminFunctionalities(Scanner sc) throws CannotConnectException {
		int adminChoice = 0;
		do {
			displayAdminFunctionalities();
			System.out.print("Please select one of the following preferences: ");
			adminChoice = sc.nextInt();
			switch (adminChoice) {
				case 1:
					addDepartment(sc);
					break;
				case 2:
					removeDepartment(sc);
					break;
				case 3:
//					modifyDepartment(sc);
					break;
				case 4:
//					findDepartmentById(sc);
					break;
				case 5:
//					addOperator(sc);
					break;
				case 6:
//					removeOperator(sc);
					break;
				case 7:
//					modifyOperator(sc);
					break;
				case 8:
//					findOperatorById(sc);
					break;
				case 9:
//					viewAllOperators();
					break;
				case 0:
					System.out.println("Admin Logout Successfully..!");
					break;
				default:
						System.out.println("🚫Please enter the correct preference and try again..!");
						break;
				
			}
		}while(adminChoice != 0);
	}

//	Admin login
	public static void adminLogin(Scanner sc) throws CannotConnectException {
		
		System.out.print("Please enter the username: ");
		String username = sc.next();
		System.out.print("Please enter the password: ");
		String password = sc.next();
		if(username.equals("admin") && password.equals("admin")) {
			System.out.println();
			App.printWelcomeMessage("Admin");
			adminFunctionalities(sc);
		}else {
			System.out.println("Invalid credentials 🤔");
		}
	}
}
