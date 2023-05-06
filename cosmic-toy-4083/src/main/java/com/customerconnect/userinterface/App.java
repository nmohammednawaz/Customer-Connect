package com.customerconnect.userinterface;
import java.util.Scanner;

import com.customerconnect.exception.CannotConnectException;

public class App {
	
//	Prints the welcome message for specific user type
	static void printWelcomeMessage(String userType) {
		System.out.println("Hey 👋 " + userType + "..! Welcome");
		System.out.println();
	}
	
//	-------------------------------------- Main Method --------------------------------------
	
    public static void main( String[] args ) throws CannotConnectException{
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
    				System.out.println();
    				AdminUI.adminLogin(sc);
    				break;
    			case 2:
    				System.out.println();
//    				CustomerUI.userIsCustomer();
    				break;
    			case 3:
    				System.out.println();
//    				OperatorUI.userIsOperator();
    				break;
    			case 0:
    				System.out.println();
    				System.out.println("Thank you for using our system!\nHave a great day!😊");
    				System.exit(0);
    				break;
    			default:
    				System.out.println();
    				System.out.println("🚫Please enter the correct preference and try again..!");
    				break;
    		}
    		System.out.println();
    	}while(choice != 0);
    	sc.close();
    }
}
