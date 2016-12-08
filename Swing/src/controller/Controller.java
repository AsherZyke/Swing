package controller;

import gui.FormEvent;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class Controller {
	Database db = new Database();
	
	public List<Person> getPeople() {
		return db.getPeople();
	}
	
	
	public void addPerson(FormEvent ev) {
		String name = ev.getName();
		String occupation = ev.getOccupation();
		int ageCat = ev.getAgeCategory();
		String empCat = ev.getEmploymentCategory();
		boolean isUs = ev.isUsCtizen();
		String taxId = ev.getTaxID();
		String gender = ev.getGender();
		
		AgeCategory ageCategory = null;
		
		switch(ageCat) {
			case 0:
				ageCategory = AgeCategory.child;
				break;
			case 1:
				ageCategory = AgeCategory.adult;
				break;
			case 2:
				ageCategory = AgeCategory.senior;
				break;
		};
		
		EmploymentCategory employment;
		
		if(empCat.equals("employed")) {
			employment = EmploymentCategory.employed;
		} else if(empCat.equals("unemployed")) {
			employment = EmploymentCategory.unemployed;
		} else {
			employment = EmploymentCategory.other;
			System.out.println(empCat);
		}
		
		Gender genderCat;
		
		if(gender.equals("male")) {
			genderCat = Gender.male;
		} else {
			genderCat = Gender.female;
		}
		
		Person person = new Person(name, occupation, ageCategory, employment, taxId, isUs, genderCat);
		
		db.addPerson(person);
	}
	
	public void removePerson(int index) {
	    db.removePerson(index);
	}
	
	public void saveToFile(File file) throws IOException {
	    db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
	    db.loadFromFile(file);
	}

}
