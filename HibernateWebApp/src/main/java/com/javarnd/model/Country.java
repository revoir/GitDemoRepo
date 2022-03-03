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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity

/*
 * //using second level ehcache for allowing read(select) and write (save)
 * operations //in Hibernate. Used Non Strict strategy as was read write
 * allowing some //intermediate tables to skip entries in join/intermediate
 * tables
 */
@Cacheable

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

@Table(name = "Country")

public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int country_id;

	private String country_name;
	/*
	 * creating all @OneToOne,@ManyToMany mapping as per project requirements
	 * applying lazy fetch not eager as to selectively apply criterias and
	 * getting particular set of result set not all as given by FetchType.EAGER
	 * using specific cascading techniques as mentioned in project exclude
	 * REMOVE as not required in our case else CascadeType.ALL would have used
	 */
	@OneToOne(fetch = FetchType.LAZY, targetEntity = Language.class, cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })
	private Language language;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = City.class, cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })

	private City Capital;

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Sport.class, cascade = { CascadeType.DETACH, CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "sports_country", joinColumns = @JoinColumn(name = "country_id"), inverseJoinColumns = @JoinColumn(name = "sports_id"))
	private List<Sport> sport;

	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Country(String country_name, Language language, City capital) {
		super();
		this.country_name = country_name;
		this.language = language;
		this.Capital = capital;
	}

	public Country(String country_name, Language language, City capital, List<Sport> sport) {
		super();
		this.country_name = country_name;
		this.language = language;
		this.Capital = capital;
		this.sport = sport;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public String getCountry_name() {
		return country_name;
	}

	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public City getCapital() {
		return Capital;
	}

	public void setCapital(City capital) {
		Capital = capital;
	}

	public List<Sport> getSport() {
		return sport;
	}

	public void setSport(List<Sport> sport) {
		this.sport = sport;
	}

	/*
	 * added addSports function to add list of sports since @ManyToMany mapping
	 * is used
	 */ 
	public void addSports(Sport tempSport) {
		if (sport == null) {
			sport = new ArrayList<Sport>();
		}
		sport.add(tempSport);
	}

	@Override
	public String toString() {
		return "Country [language=" + language + ", Capital=" + Capital + ", sport=" + sport + "]";
	}

}
