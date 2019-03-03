package com.persistence.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateSession {
	private static final SessionFactory sessionFactory = builSessionFactory();
	private static Session session;
	
	private static SessionFactory builSessionFactory(){
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		return sessionFactory;
		
	}
//	public static SessionFactory getSessionFactory(){
//		return sessionFactory;
//	}
//	public static Session getSession(){
//		if(null == session){
//			session = sessionFactory.openSession();
//		}
//		return session;
//	}
}
