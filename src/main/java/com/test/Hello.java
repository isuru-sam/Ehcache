package com.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#cache
 * https://tehaleankush.blogspot.com/2013/01/caching-in-spring-with-ehcache-and-maven.html
 * https://marschall.github.io/2017/10/01/better-spring-cache-key-generator.html
 * @author ASUS-PC
 *
 */
@RestController
public class Hello {

	@Autowired
	Employee employee;
	
	@RequestMapping("/customers")
	
	public String getCustomer() {
		//employee.setName("Isuru");
		
		
		return employee.getName();
	}
	
	
@RequestMapping("/details1")
	
	public String getDetails1() {
		//employee.setName("Isuru");
		
		
		return employee.getDetails("A", false, true).toString();
	}

@RequestMapping("/details2")

public String getDetails2() {
	//employee.setName("Isuru");
	
	
	return employee.getDetails("A", true, true).toString();
}

@RequestMapping("/details3")

public String getDetails3() {
	//employee.setName("Isuru");
	
	
	return employee.getDetails("B", true, true).toString();
}
	
}
 