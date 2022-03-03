package com.javarnd.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity

@Cacheable

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Table(name = "Language")
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int language_id;
	private String language_name;
	/*
	 * creating all @OneToMany mapping as per project requirements applying lazy
	 * fetch not eager as to selectively apply criterias and getting particular set
	 * of result set not all as given by FetchType.EAGER using specific cascading
	 * techniques as mentioned in project exclude REMOVE as not required in our case
	 * else CascadeType.ALL would have used
	 */
	@OneToMany(fetch = FetchType.LAZY, targetEntity = Country.class, cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })

	private List<Country> country;

	public Language() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Country> getCountry() {
		return country;
	}

	public void setCountry(List<Country> country) {
		this.country = country;
	}

	public Language(int language_id, String language_name) {
		super();
		this.language_id = language_id;
		this.language_name = language_name;
	}

	public Language(String language_name) {
		super();
		this.language_name = language_name;
	}

	public int getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}

	public String getLanguage_name() {
		return language_name;
	}

	public void setLanguage_name(String language_name) {
		this.language_name = language_name;
	}

	// using addcountry to add list of countries since @OneToMany mapping is being
	// used
	public void addCountry(Country tempCountry) {
		if (country == null) {
			country = new ArrayList<Country>();
		}
		country.add(tempCountry);
	}

	@Override
	public String toString() {
		return "Language [country=" + country + "]";
	}

}
