package com.azovstal.poly;

public class Employer {
	private String name;
	private Integer age;
	private Double salary;
	
	public Employer() {
		// TODO Auto-generated constructor stub
	}
	

	public Employer(String name, Integer age, Double salary) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
}
