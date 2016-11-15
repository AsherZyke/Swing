package model;

public class Person {
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
    	this.taxID = taxID;
    	this.usCitizen = usCitizen;
    	this.gender = gender;
    	
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
		this.occupation = occupation;
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