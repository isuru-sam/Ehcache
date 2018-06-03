package com.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service("employee")
@EnableCaching
public class Employee {
	String name = "Isuru";
	String addres;

	@Cacheable("testCache")
	public String getName() {
		System.out.println("Get Name called");
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}
	@Cacheable(cacheNames={"testCache"}, keyGenerator="workingKeyGenerator")
	public List<String> getDetails(String name, boolean gender, boolean age) {
System.out.println("Invoking getDetails"+name+":"+gender+":"+age);
	List<String> t = new ArrayList<String>();	
		if (name.equalsIgnoreCase("A")) {
			t.add("A");
		
		} else if (name.equals("B")) {
			t.add("B");
			
		} else t.add("C");
			if(gender)
				t.add("Gender");
			else 
				t.add("No gender");
			if(age)
				t.add("age");
			else 
				t.add("No age");
			

		
		return t;

	}

}
