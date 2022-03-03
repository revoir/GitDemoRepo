package com.javarnd.model;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity

@Cacheable

@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)

public class City{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int city_id;
	private String city_name;
	/*
	 * creating all @OneToOne mapping as per project requirements applying lazy
	 * fetch not eager as to selectively apply criterias and getting particular
	 * set of result set not all as given by FetchType.EAGER using specific
	 * cascading techniques as mentioned in project exclude REMOVE as not required
	 * in our case else CascadeType.ALL would have used
	 */	
	@OneToOne(fetch = FetchType.LAZY,targetEntity = Country.class, cascade = { CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH })
	private Country country;


	public City() {
		super();
		// TODO Auto-generated constructor stub
	}


	public City(int city_id, String city_name) { 
		super(); 
		this.city_id = city_id;
		this.city_name = city_name; }


	public City(String city_name) {
		super();
		this.city_name = city_name;
	}

	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}


	@Override public String toString() { return "City [city_id=" + city_id +
			", city_name=" + city_name + "]"; }


}
