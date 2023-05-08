package com.customerconnect.enumholder;

public enum UserType {
	CUSTOMER("Customer"),
	OPERATOR("Operator"),
	ADMIN("Admin");
	
	private String userType;

	private UserType(String userType) {
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
