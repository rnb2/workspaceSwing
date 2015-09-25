package com.azovstal.poly;

public class Manager extends Employer {
	
	private Integer bonus;
	
	
	public Manager(String name, Integer age, Double salary) {
		super(name, age, salary);
		this.bonus = 0;
	}


	@Override
	public Double getSalary() {
		double salary =  super.getSalary();
		return salary  + bonus;
	}


	public Integer getBonus() {
		return bonus;
	}

	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}
	

}
