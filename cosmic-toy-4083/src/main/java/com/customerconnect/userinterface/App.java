package com.customerconnect.userinterface;
import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App {
//	Prints the welcome message for specific user type
	static void printWelcomeMessage(String userType) {
		System.out.println("Hey 👋 " + userType + "..! Welcome");
	}
	
//	Admin login
	static void userIsAdmin(Scanner sc) {
		System.out.print("Please enter the username: ");
		String username = sc.next();
		System.out.print("Please enter the password: ");
		String password = sc.next();
		if(username.equals("admin") && password.equals("admin")) {
//			adminFunctionalities(sc);
		}else {
			System.out.println("Invalid credentials 🤔");
		}
	}
	
	
//	-------------------------------------- Main Method --------------------------------------
	
    public static void main( String[] args ){
//    	Scanner object to the take input from user
    	Scanner sc = new Scanner(System.in);
    	int choice = 0;
    	do {
    		System.out.println("1. Admin");
    		System.out.println("2. Customer");
    		System.out.println("3. Operator");
    		System.out.println("0. Exit");
    		System.out.print("Please select one of the following preferences: ");
    		
    		choice = sc.nextInt();
    		switch(choice) {
    			case 1:
    				printWelcomeMessage("Admin");
    				userIsAdmin(sc);
    				break;
    			case 2:
//    				userIsCustomer();
    				break;
    			case 3:
//    				userIsOperator();
    				break;
    			case 0:
    				System.out.println();
    				System.out.println("Thank you for using our system!\nHave a great day!😊");
    				break;
    			default:
    				System.out.println("🚫Please enter the correct preference and try again..!");
    				break;
    		}
    		System.out.println();
    	}while(choice != 0);
    	sc.close();
    }
}
