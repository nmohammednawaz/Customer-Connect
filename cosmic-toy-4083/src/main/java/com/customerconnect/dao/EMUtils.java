package com.customerconnect.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMUtils {
	static EntityManagerFactory entityManagerFactory;
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("customerconnect");
	}
	
	public static EntityManager openConnection() {
		return entityManagerFactory.createEntityManager();
	}
	
	public static void closeConnection() {
		if(entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}
	
}
