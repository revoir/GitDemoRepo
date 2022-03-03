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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity

@Cacheable

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Table(name = "Sport")
public class Sport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sport_id;
	private String sport_name;

	/*
	 * creating all @ManyToMany mapping as per project requirements applying lazy
	 * fetch not eager as to selectively apply criterias and getting particular set
	 * of result set not all as given by FetchType.EAGER using specific cascading
	 * techniques as mentioned in project exclude REMOVE as not required in our case
	 * else CascadeType.ALL would have used using join table since @ManyToMany
	 * mapping will have a join column everytime
	 */
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Country.class, cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "sports_country", joinColumns = @JoinColumn(name = "sports_id"), inverseJoinColumns = @JoinColumn(name = "country_id")

	)
	private List<Country> country;

	public Sport() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sport(int sport_id, String sport_name) {
		super();
		this.sport_id = sport_id;
		this.sport_name = sport_name;
	}

	public Sport(String sport_name) {
		super();
		this.sport_name = sport_name;
	}

	public int getSport_id() {
		return sport_id;
	}

	public void setSport_id(int sport_id) {
		this.sport_id = sport_id;
	}

	public String getSport_name() {
		return sport_name;
	}

	public void setSport_name(String sport_name) {
		this.sport_name = sport_name;
	}

	public List<Country> getCountry() {
		return country;
	}

	public void setCountry(List<Country> country) {
		this.country = country;
	}

	public void addCountry(Country tempCountry) {
		if (country == null) {
			country = new ArrayList<Country>();
		}
		country.add(tempCountry);

	}

	@Override
	public String toString() {
		return "Sport [sport_id=" + sport_id + ", sport_name=" + sport_name + "]";
	}

}
