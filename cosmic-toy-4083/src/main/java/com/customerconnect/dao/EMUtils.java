package com.customerconnect.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMUtils {
	static EntityManagerFactory entityManagerFactory;
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("customerconnect");
	}
	
	static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
