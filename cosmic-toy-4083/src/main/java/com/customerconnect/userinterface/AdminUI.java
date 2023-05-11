package com.customerconnect.userinterface;

import java.util.List;
import java.util.Scanner;

import com.customerconnect.entity.Customer;
import com.customerconnect.entity.Department;
import com.customerconnect.entity.Login;
import com.customerconnect.entity.Operator;
import com.customerconnect.enumholder.UserType;
import com.customerconnect.exception.CannotCompleteTaskException;
import com.customerconnect.exception.CannotConnectException;
import com.customerconnect.exception.NoRecordFoundException;
import com.customerconnect.serviceimplementations.AdminServiceImplement;
import com.customerconnect.services.AdminService;

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
		System.out.println("10. View All Customers");
		System.out.println("11. Find Customer By Id");
		System.out.println("12. Find Customer By Email");
		System.out.println("13. Find Customer By Name");
		System.out.println("14. View Login Details");
		System.out.println("15. Lock Customer");
		System.out.println("0. Logout");
		System.out.println();
	}
	
	
//	Add department to the system
	static void addDepartment(Scanner sc){
		System.out.println("1. Technical Issue");
		System.out.println("2. Product Issue");
		System.out.println("3. Billing And Payment Issue");
		System.out.println("4. General Related Query");
		System.out.println();
		System.out.print("Please select the department name: ");
		System.out.println();
		int departmentType = sc.nextInt();
		String departmentName = null;
		switch (departmentType) {
			case 1:
				departmentName = "Technical Issue";
				break;
			case 2:
				departmentName = "Product Issue";
				break;
			case 3:
				departmentName = "Billing And Payment Issue";
				break;
			case 4:
				departmentName = "General Related Query";
				break;
			default:
				System.out.println("Unexpected value: " + departmentType);
		}
		Department department = new Department();
		department.setDepartmentName(departmentName);
		
		AdminService departmentService = new AdminServiceImplement();
		
		try {
			departmentService.addDepartment(department);
			System.out.println();
			App.printStar(185);
			App.printSpace(80);
			System.out.println("Department Added Successfully...!");
			App.printStar(185);
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
		
	}
	
	
//	Remove Departmnent
	static void removeDepartment(Scanner sc){
		System.out.println();
		System.out.print("Please enter department Id to be removed: ");
		int departmentId = sc.nextInt();
	
		AdminService departmentService = new AdminServiceImplement();
		
		try {
			departmentService.removeDepartment(departmentId);
			System.out.println();
			App.printStar(185);
			App.printSpace(80);
			System.out.println("Department Removed Successfully...!");
			App.printStar(185);
			System.out.println();
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}

//	Modify Department
	static void modifyDepartment(Scanner sc){
		System.out.println();
		System.out.print("Please enter department Id to be modified: ");
		int departmentId = sc.nextInt();
		
		AdminService departmentService = new AdminServiceImplement();
		
		try {
			Department department = departmentService.findDepartmentById(departmentId);
			sc.nextLine();
			System.out.print("Please enter department name to be modified: ");
			String departmentName = sc.nextLine();
			department = new Department();
			department.setDepartmentId(departmentId);
			department.setDepartmentName(departmentName);
			
			departmentService.modifyDepartment(department);
			System.out.println();
			App.printStar(185);
			App.printSpace(80);
			System.out.println("Department Updated Successfully..!");
			App.printStar(185);
			System.out.println();
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
		
		
	}
	
//	Find Department By Id
	static void findDepartmentById(Scanner sc){
		System.out.print("Please enter department Id: ");
		int departmentId = sc.nextInt();
		
		AdminService departmentService = new AdminServiceImplement();
		
		try {
			Department department = departmentService.findDepartmentById(departmentId);
			System.out.println();
			System.out.println("Department Id: " + department.getDepartmentId() + "\nDepartment Name: " + department.getDepartmentName().toUpperCase());
			System.out.println();
			App.printStar(185);
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}
	
//	Add Operator
	static void addOperator(Scanner sc){
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
		System.out.println("Please select the department name: ");
		System.out.println("1. Technical Issue");
		System.out.println("2. Product Issue");
		System.out.println("3. Billing And Payment Issue");
		System.out.println("4. General Related Query");
		System.out.println();
		System.out.print("Please enter your selection: ");
		System.out.println();
		
		int operatorDepartmentid = sc.nextInt();
		if(operatorDepartmentid > 4) {
			System.out.println("Please select a valid department...!");
			return;
		}
		
//		Creating Operator Object
		Operator operator = new Operator();
		operator.setFirstName(operatorFirstName);
		operator.setLastName(operatorLastName);
		operator.setEmail(operatorEmail);
		operator.setMobileNumber(operatorMobile);
		operator.setCity(operatorCity);
		
		
		AdminService departmentService = new AdminServiceImplement();
		try {
//			Finds for the department by department id and set the department for the operator
			Department department = departmentService.findDepartmentById(operatorDepartmentid);
			operator.setDepartment(department);
			
			AdminService operatorService = new AdminServiceImplement();
			operatorService.addOperator(operator);
			System.out.println();
			App.printStar(185);
			App.printSpace(80);
			System.out.println("Operator Added Successfully...!");
			App.printStar(185);
			System.out.println();
		}catch (CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
		
	}
	
//	Remove operator
	static void removeOperator(Scanner sc){
		System.out.println();
		System.out.print("Please enter operator Id to be removed: ");
		int operatortId = sc.nextInt();
	
		AdminService operatorService = new AdminServiceImplement();
		
		try {
			operatorService.removeOperator(operatortId);
			System.out.println();
			App.printStar(185);
			App.printSpace(80);
			System.out.println("Operator Removed Successfully...!");
			App.printStar(185);
			System.out.println();
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}
	
//	Modify Operator
	static void modifyOperator(Scanner sc){
		System.out.println();
		System.out.print("Please enter operator Id to be modified: ");
		int operatorId = sc.nextInt();
		
		AdminService operatorService = new AdminServiceImplement();
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
			
//			Creating Operator object to Update
			operator = new Operator();
			operator.setOperatorId(operatorId);
			operator.setFirstName(operatorfirstName);
			operator.setLastName(operatorLastName);
			operator.setEmail(operatorEmail);
			operator.setMobileNumber(operatorMobile);
			operator.setCity(operatorCity);
			
			AdminService departmentService = new AdminServiceImplement();
			Department department = departmentService.findDepartmentById(operatorDepartmentid);
			operator.setDepartment(department);
			
			operatorService.modifyOperator(operator);
			System.out.println();
			App.printStar(185);
			App.printSpace(80);
			System.out.println("Operator Updated Successfully..!");
			App.printStar(185);
			System.out.println();
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}
	
//	Find Operator By Id
	static void findOperatorById(Scanner sc){
		System.out.println();
		System.out.print("Please enter operator Id: ");
		int operatorId = sc.nextInt();
		
		AdminService operatorService = new AdminServiceImplement();
		
		try {
			Operator operator = operatorService.findOperatorById(operatorId);
			System.out.println();
			System.out.println("Operator Id: " + operator.getOperatorId() + 
							   "\nOperator Name: " + operator.getFirstName() + " " + operator.getLastName() +
							   "\nOperator Email Address: " + operator.getEmail() +
							   "\nOperator Mobile Number: " + operator.getMobileNumber() + 
							   "\nOperator City: " + operator.getCity() + "\n"
							   );
			System.out.println();
			App.printStar(185);
		}catch(CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}
	
//	View All Operators
	static void viewAllOperators(){
		AdminService operatorService = new AdminServiceImplement();
		try {
			List<Operator> operatorsList = operatorService.viewAllOperators();
			
			operatorsList.forEach(operator -> System.out.println("Operator Id: " + operator.getOperatorId() + 
					   "\nOperator Name: " + operator.getFirstName() + " " + operator.getLastName() +
					   "\nOperator Email Address: " + operator.getEmail() +
					   "\nOperator Mobile Number: " + operator.getMobileNumber() + 
					   "\nOperator City: " + operator.getCity() + "\n"
					   ));
			
		}catch(NoRecordFoundException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}
	
//	View All Customers
	private static void viewAllCustomers() {
		
		AdminService operatorService = new AdminServiceImplement();
		try {
			List<Customer> customersList = operatorService.viewAllCustomers();
			System.out.println();
			customersList.forEach(customer -> System.out.println("Customer Id: " + customer.getCustomerId() + 
					   "\nCustomer Name: " + customer.getFirstName() + " " + customer.getLastName() +
					   "\nCustomer Email Address: " + customer.getEmail() +
					   "\nCustomer Mobile Number: " + customer.getMobileNumber() + 
					   "\nCustomer City: " + customer.getCity() + "\n"
					   ));
			System.out.println();
			App.printStar(185);
		}catch(NoRecordFoundException noRecordFoundException) {
			System.out.println(noRecordFoundException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}
	
//	Find Customer By Id
	private static void findCustomerById(Scanner sc) {
		System.out.println();
		System.out.print("Please enter customer id: ");
		int customerId = sc.nextInt();
		try {
			AdminService adminService = new AdminServiceImplement();
			Customer customer = adminService.findCustomerById(customerId);
			System.out.println();
			System.out.println("Customer Id: " + customer.getCustomerId() + 
					   "\nCustomer Name: " + customer.getFirstName() + " " + customer.getLastName() +
					   "\nCustomer Email Address: " + customer.getEmail() +
					   "\nCustomer Mobile Number: " + customer.getMobileNumber() + 
					   "\nCustomer City: " + customer.getCity() + "\n"
					   );
			System.out.println();
			App.printStar(185);
		}catch(NoRecordFoundException noRecordFoundException) {
			System.out.println(noRecordFoundException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
		
	}
	
//	find customers by name
	private static void findCustomerByEmail(Scanner sc) {
		System.out.println();
		System.out.print("Please enter customer email address: ");
		String customerEmail = sc.next();
		
		if(!customerEmail.contains("@") && !customerEmail.contains(".")) {
			System.out.println("Dear Admin, Please enter correct email address and try again...!");
		}
		
		try {
			AdminService adminService = new AdminServiceImplement();
			Customer customer = adminService.findCustomerByEmail(customerEmail);
			System.out.println();
			System.out.println("Customer Id: " + customer.getCustomerId() + 
					   "\nCustomer Name: " + customer.getFirstName() + " " + customer.getLastName() +
					   "\nCustomer Email Address: " + customer.getEmail() +
					   "\nCustomer Mobile Number: " + customer.getMobileNumber() + 
					   "\nCustomer City: " + customer.getCity() + "\n"
					   );
			System.out.println();
			App.printStar(185);
		}catch(NoRecordFoundException noRecordFoundException) {
			System.out.println(noRecordFoundException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}	
	}
	
//	Find Customers by name
	private static void findCustomersByName(Scanner sc) {
		System.out.println();
		sc.nextLine();
		System.out.print("Please enter fisrt name of customer: ");
		String customerFirstName = sc.nextLine();
		System.out.print("Please enter last name of customer: ");
		String customerLastName = sc.nextLine();
		
		try {
			AdminService adminService = new AdminServiceImplement();
			List<Customer> customersList = adminService.findCustomersByName(customerFirstName, customerLastName);
			
			System.out.println();
			customersList.forEach(customer -> System.out.println("Customer Id: " + customer.getCustomerId() + 
					   "\nCustomer Name: " + customer.getFirstName() + " " + customer.getLastName() +
					   "\nCustomer Email Address: " + customer.getEmail() +
					   "\nCustomer Mobile Number: " + customer.getMobileNumber() + 
					   "\nCustomer City: " + customer.getCity() + "\n"
					   ));
			System.out.println();
			App.printStar(185);
		}catch(NoRecordFoundException noRecordFoundException) {
			System.out.println(noRecordFoundException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}
	
//	Block Customer Account
	private static void blockCustomerAccount(Scanner sc) {
		System.out.println();
		System.out.print("Please enter customer id to block: ");
		int customerId = sc.nextInt();
		
		try {
			AdminService adminService = new AdminServiceImplement();
			adminService.blockCustomer(customerId);
			System.out.println();
			App.printStar(185);
			App.printSpace(65);
			System.out.println("Customer with id " + customerId + " is successfully blocked...!");
			App.printStar(185);
		}catch(NoRecordFoundException noRecordFoundException) {
			System.out.println(noRecordFoundException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		} catch (CannotCompleteTaskException cannotCompleteTaskException) {
			System.out.println(cannotCompleteTaskException.getMessage());
		}
	}
	
//	View All Login Details
	private static void viewLoginDetails() {
		try {
			AdminService adminService = new AdminServiceImplement();
			List<Login> loginList = adminService.getLoginDetails();
			System.out.println();
			loginList.forEach( login -> System.out.println("Login Id: " + login.getId() + "\nLogin User Type: " + login.getUserType() + "\nLogin Username: " +login.getUsername() + "\n"));
			System.out.println();
			App.printSpace(185);
		}catch(NoRecordFoundException noRecordFoundException) {
			System.out.println(noRecordFoundException.getMessage());
		} catch (CannotConnectException cannotConnectException) {
			System.out.println(cannotConnectException.getMessage());
		}
	}
	
//	Admin Functionalities
	static void adminFunctionalities(Scanner sc) throws CannotConnectException {
		int adminChoice = 0;
		do {
			displayAdminFunctionalities();
			System.out.print("Please select one of the following preferences: ");
			System.out.println();
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
				case 10:
					viewAllCustomers();
					break;
				case 11:
					findCustomerById(sc);
					break;
				case 12:
					findCustomerByEmail(sc);
					break;
				case 13:
					findCustomersByName(sc);
					break;
				case 14:
					viewLoginDetails();
					break;
				case 15:
					blockCustomerAccount(sc);
					break;
				case 0:
					System.out.println();
					App.printStar(185);
					App.printSpace(80);
					System.out.println("Admin Logout Successfully..!");
					App.printStar(185);
					System.out.println();
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
			userType.setUserType("ADMIN");
			System.out.println();
			App.printStar(185);
			App.printSpace(85);
			App.printWelcomeMessage(userType.getUserType());
			App.printStar(185);
			adminFunctionalities(sc);
		}else {
			System.out.println();
			App.printStar(185);
			App.printSpace(80);
			System.out.println("Invalid credentials 🤔");
			App.printStar(185);
			System.out.println();
		}
	}
}
