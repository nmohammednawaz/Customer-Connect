package com.customerconnect.userinterface;

import java.util.List;
import java.util.Scanner;

import com.customerconnect.entity.Department;
import com.customerconnect.entity.Operator;
import com.customerconnect.enumholder.UserType;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;
import com.customerconnect.serviceimplementations.DepartmentServiceImplements;
import com.customerconnect.serviceimplementations.OperatorServiceImplement;
import com.customerconnect.services.DepartmentService;
import com.customerconnect.services.OperatorService;

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
		System.out.print("Please enter the department name: ");
		sc.nextLine();
		String departmentName = sc.nextLine();
		
		Department department = new Department();
		department.setDepartmentName(departmentName);
		
		DepartmentService departmentService = new DepartmentServiceImplements();
		
		try {
			departmentService.addDepartment(department);
			System.out.println("Department Added Successfully...!");
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
		
	}
	
	
//	Remove Departmnent
	static void removeDepartment(Scanner sc) throws CannotConnectException{
		System.out.print("Please enter department Id to be removed: ");
		int departmentId = sc.nextInt();
	
		DepartmentService departmentService = new DepartmentServiceImplements();
		
		try {
			departmentService.removeDepartment(departmentId);
			System.out.println("Department Removed Successfully...!");
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
	}

//	Modify Department
	static void modifyDepartment(Scanner sc) throws CannotConnectException{
		System.out.print("Please enter department Id to be modified: ");
		int departmentId = sc.nextInt();
		
		DepartmentService departmentService = new DepartmentServiceImplements();
		
		try {
			Department department = departmentService.findDepartmentById(departmentId);
			sc.nextLine();
			System.out.print("Please enter department name to be modified: ");
			String departmentName = sc.nextLine();
			department = new Department();
			department.setDepartmentId(departmentId);
			department.setDepartmentName(departmentName);
			
			departmentService.modifyDepartment(department);
			System.out.println("Department Updated Successfully..!");
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
		
		
	}
	
//	Find Department By Id
	static void findDepartmentById(Scanner sc) throws CannotConnectException{
		System.out.print("Please enter department Id: ");
		int departmentId = sc.nextInt();
		
		DepartmentService departmentService = new DepartmentServiceImplements();
		
		try {
			Department department = departmentService.findDepartmentById(departmentId);
			System.out.println(department.getDepartmentId() + " | " + department.getDepartmentName());
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
	}
	
//	Add Operator
	static void addOperator(Scanner sc) throws CannotConnectException{
		System.out.print("Please enter first name of operator: ");
		sc.nextLine();
		String operatorFirstName = sc.nextLine();
		System.out.print("Please enter last name of operator: ");
		String operatorLastName = sc.nextLine();
		System.out.print("Please enter email of operator: ");
		String operatorEmail = sc.next();
		System.out.print("Please enter mobile number of operator: ");
		String operatorMobile = sc.next();
		System.out.print("Please enter city of operator: ");
		String operatorCity = sc.next();
		System.out.print("Please enter operator department id: ");
		int operatorDepartmentid = sc.nextInt();
		
		Operator operator = new Operator();
		operator.setFirstName(operatorFirstName);
		operator.setLastName(operatorLastName);
		operator.setEmail(operatorEmail);
		operator.setMobileNumber(operatorMobile);
		operator.setCity(operatorCity);
		
		DepartmentService departmentService = new DepartmentServiceImplements();
		try {
//			Finds for the department by department id and set the department for the operator
			Department department = departmentService.findDepartmentById(operatorDepartmentid);
			operator.setDepartment(department);
			
			OperatorService operatorService = new OperatorServiceImplement();
			operatorService.addOperator(operator);
			
			System.out.println("Operator Added Successfully...!");
		}catch (CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
		
	}
	
//	Remove operator
	static void removeOperator(Scanner sc) throws CannotConnectException{
		System.out.print("Please enter operator Id to be removed: ");
		int operatortId = sc.nextInt();
	
		OperatorService operatorService = new OperatorServiceImplement();
		
		try {
			operatorService.removeOperator(operatortId);
			System.out.println("Operator Removed Successfully...!");
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
	}
	
//	Modify Operator
	static void modifyOperator(Scanner sc) throws CannotConnectException{
		System.out.print("Please enter operator Id to be modified: ");
		int operatorId = sc.nextInt();
		
		OperatorService operatorService = new OperatorServiceImplement();
		try {
			Operator operator = operatorService.findOperatorById(operatorId);
			sc.nextLine();
			System.out.print("Please enter first name of operator to be modified: ");
			String operatorfirstName = sc.nextLine();
			System.out.print("Please enter last name of operator to be modified: ");
			String operatorLastName = sc.nextLine();
			System.out.print("Please enter email of operator to be modified: ");
			String operatorEmail = sc.next();
			System.out.print("Please enter mobile number of operator to be modified: ");
			String operatorMobile = sc.next();
			System.out.print("Please enter city of operator to be modified: ");
			String operatorCity = sc.next();
			System.out.print("Please enter operator department id to be modified: ");
			int operatorDepartmentid = sc.nextInt();
			operator = new Operator();
			operator.setOperatorID(operatorId);
			operator.setFirstName(operatorfirstName);
			operator.setLastName(operatorLastName);
			operator.setEmail(operatorEmail);
			operator.setMobileNumber(operatorMobile);
			operator.setCity(operatorCity);
			
			DepartmentService departmentService = new DepartmentServiceImplements();
			Department department = departmentService.findDepartmentById(operatorDepartmentid);
			operator.setDepartment(department);
			
			operatorService.modifyOperator(operator);
			
			System.out.println("Operator Updated Successfully..!");
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
	}
	
//	Find Operator By Id
	static void findOperatorById(Scanner sc) throws CannotConnectException{
		System.out.print("Please enter operator Id: ");
		int operatorId = sc.nextInt();
		
		OperatorService operatorService = new OperatorServiceImplement();
		
		try {
			Operator operator = operatorService.findOperatorById(operatorId);
			System.out.println(operator.getFirstName() + " " + operator.getLastName());
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
	}
	
//	View All Operators
	static void viewAllOperators() throws CannotConnectException{
		OperatorService operatorService = new OperatorServiceImplement();
		try {
			List<Operator> operatorsList = operatorService.viewAllOperators();
			
			operatorsList.forEach(operator -> System.out.println(operator.getFirstName() + " " + operator.getLastName() + " - " + operator.getDepartment().getDepartmentName()));
			
		}catch(NoRecordFoundException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
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
					modifyDepartment(sc);
					break;
				case 4:
					findDepartmentById(sc);
					break;
				case 5:
					addOperator(sc);
					break;
				case 6:
					removeOperator(sc);
					break;
				case 7:
					modifyOperator(sc);
					break;
				case 8:
					findOperatorById(sc);
					break;
				case 9:
					viewAllOperators();
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
			UserType userType = UserType.ADMIN;
			userType.setUserType("Admin");
			System.out.println(userType.getUserType());
			App.printWelcomeMessage(userType.getUserType());
			adminFunctionalities(sc);
		}else {
			System.out.println("Invalid credentials 🤔");
		}
	}
}
