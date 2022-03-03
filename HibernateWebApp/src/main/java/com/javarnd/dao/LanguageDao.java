package com.javarnd.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.javarnd.model.Country;
import com.javarnd.model.Language;
import com.javarnd.util.HibernateUtil;

public class LanguageDao {

//getLanguage get list of countries that are being added for the same language being spoken

	@SuppressWarnings("unchecked")
	public List<Language> getLanguage(String name) {

		Transaction transaction = null;
		List<Language> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			transaction = session.beginTransaction();
			Criteria recordDetails = session.createCriteria(Language.class);
			recordDetails.add(Restrictions.ilike("language_name", name));
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

//getAllLanguages method using HQL/SQL queries to get distinct language once doGet() is being triggered for search

	@SuppressWarnings("unchecked")
	public List<Language> getAllLanguages() {
		Transaction transaction = null;
		List<Language> listOfUser = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			transaction = session.beginTransaction();
			listOfUser = session.createQuery("select distinct language_name from Language").list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		return listOfUser;
	}

}
