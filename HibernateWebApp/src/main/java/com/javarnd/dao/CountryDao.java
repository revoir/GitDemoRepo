package com.javarnd.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.javarnd.model.City;
import com.javarnd.model.Country;
import com.javarnd.model.Language;
import com.javarnd.model.Sport;
import com.javarnd.util.HibernateUtil;

public class CountryDao {

//SaveConuntry is used to register all meta information related to a country

	public void SaveCountry(String name, String language, String city, String sport) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			transaction = session.beginTransaction();
			// Transient objects
			Country c = new Country();
			City c1 = new City();
			Language l1 = new Language();
			Sport s1 = new Sport();

			c.setCountry_name(name);
			l1.setLanguage_name(language);
			c1.setCity_name(city);
			s1.setSport_name(sport);

			c.setLanguage(l1);
			l1.addCountry(c);
			c.setCapital(c1);
			c1.setCountry(c);
			s1.addCountry(c);
			// creating persistent objects
			session.persist(c);
			session.persist(c1);
			session.persist(s1);
			session.persist(l1);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			// closing session and session factory
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}

	/*
	 * getCountry method used for fetching data related to a country i.e If I fetch
	 * an object of country then I should be able to fetch its language, capital
	 * city and sports played in the country.
	 */

	@SuppressWarnings("unchecked")
	public List<Country> getCountry(String name) {

		Transaction transaction = null;
		List<Country> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			transaction = session.beginTransaction();
			Criteria recordDetails = session.createCriteria(Country.class);
			recordDetails.add(Restrictions.ilike("country_name", name));
			list = recordDetails.list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		return list;
	}

//getAllCountries using HQL/SQL queries to get distinct country names once the doGet() method gets hit from the servlet

	@SuppressWarnings("unchecked")
	public List<Country> getAllCountries() {
		Transaction transaction = null;
		List<Country> listOfUser = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			transaction = session.beginTransaction();
			listOfUser = session.createQuery("select distinct country_name from Country").list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
		return listOfUser;
	}

}
