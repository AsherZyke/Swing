package model;

import java.io.Serializable;

public class Person implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = -2566745671938885455L;

    private static int count = 0;

	
	private int id;
	private String name;
    private String occupation;
    private AgeCategory ageCategory;
    private EmploymentCategory empCat;
    private String taxID;
    private boolean usCitizen;
    private Gender gender;
    
    public Person(String name, String occupation, AgeCategory ageCategory,
    		EmploymentCategory empCat, String taxID, boolean usCitizen,
    		Gender gender) {
    	this.name = name;
    	this.occupation = occupation;
    	this.ageCategory = ageCategory;
    	this.empCat = empCat;
    	this.usCitizen = usCitizen;
    	this.gender = gender;
    	if(usCitizen) {
    	    this.taxID = taxID;
    	} else {
    	    this.taxID = null;
    	}
    	
    	this.id = count;
    	count++;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
	}
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}
	public EmploymentCategory getEmpCat() {
		return empCat;
	}
	public void setEmpCat(EmploymentCategory empCat) {
		this.empCat = empCat;
	}
	public String getTaxID() {
		return taxID;
	}
	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}
	public boolean isUsCtizen() {
		return usCitizen;
	}
	public void setUsCtizen(boolean usCtizen) {
		this.usCitizen = usCtizen;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
