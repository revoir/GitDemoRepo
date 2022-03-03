package com.javarnd.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.javarnd.model.City;
import com.javarnd.model.Country;
import com.javarnd.model.Language;
import com.javarnd.model.Sport;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		/*
		 * Singleton design for calling session factory from config file as when
		 * required ,Singleton session Factory with multiple sessions creation approach
		 */
		try {
			Configuration configuration = new Configuration();

			configuration.configure("com/javarnd/resource/hibernate.cfg.xml").addAnnotatedClass(Country.class)
					.addAnnotatedClass(City.class).addAnnotatedClass(Language.class).addAnnotatedClass(Sport.class);

			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry();
			System.out.println("Hibernate Java Config serviceRegistry created");
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sessionFactory;
	}
}