package com.customerconnect.userinterface;
import java.util.Scanner;
import com.customerconnect.exception.CannotConnectException;

public class App {
	
//	Prints the welcome message for specific user type
	static void printWelcomeMessage(String userType) {
		System.out.println("Hey 👋 " + userType + "..! Welcome");
	}
	
//	Prints Space
	public static void printSpace(int n) {
		for(int i = 0; i < n; i++) {
			System.out.print(" ");
		}
	}
	
//	prints Star
	public static void printStar(int n) {
		for(int i = 0; i < n; i++) {
			System.out.print("*");
		}
		System.out.println();	
	}
	
//	-------------------------------------- Main Method --------------------------------------
	
    public static void main( String[] args ) throws CannotConnectException{
//    	Scanner object to the take input from user
    	Scanner sc = new Scanner(System.in);
    	printStar(185);
    	printSpace(74);
    	System.out.println("🎉🎉🎉 WELCOME TO CUSTOMER CONNECT 🎉🎉🎉 ");
    	printStar(185);
    	int choice = 0;
    	do {
    		printSpace(88);
    		System.out.println("1. Admin");
    		printSpace(88);
    		System.out.println("2. Customer");
    		printSpace(88);
    		System.out.println("3. Operator");
    		printSpace(88);
    		System.out.println("0. Exit");
    		System.out.println();
    		printSpace(72);
    		System.out.print("Please select one of the following preferences: ");
    		choice = sc.nextInt();
    		switch(choice) {
    			case 1:
    				System.out.println();
    				printStar(185);
    				AdminUI.adminLogin(sc);
    				System.out.println();
    				printStar(185);
    				break;
    			case 2:
    				System.out.println();
    				printStar(185);
    				CustomerUI.userIsCustomer(sc);
    				System.out.println();
    				printStar(185);
    				break;
    			case 3:
    				System.out.println();
    				printStar(185);
    				OperatorUI.operatorLogin(sc);
    				System.out.println();
    				printStar(185);
    				break;
    			case 0:
    				System.out.println();
    				printStar(185);
    				printSpace(70);	
    				System.out.println("Thank you for using our system, Have a great day!😊");
    				printStar(185);
    				System.exit(1);
    				break;
    			default:
    				System.out.println();
    				printStar(185);
    				printSpace(68);
    				System.out.println("🚫Please enter the correct preference and try again..!");
    				printStar(185);
    				break;
    		}
    		System.out.println();
    	}while(choice != 0);
    	sc.close();
    }
}
