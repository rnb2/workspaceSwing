package com.azovstal.poly;

public class Example {
	
	public Example() {
		Employer[] staff = new Employer[3];
		
		Manager boss = new Manager("Boss", 25, 100.00 );
				boss.setBonus(1000);
		staff[0] = boss;
		staff[1] = new Employer("Chel1", 50, 50.00); 
		staff[2] = new Employer("Chel2", 55, 45.00);
			for (int i = 0; i < staff.length; i++) {
				Employer e = staff[i];
				System.out.println("budukh-rn-----29.05.2008-----"+e.getName() + " " + e.getSalary());
			}
	}
	
	public void doSomsthing(Employer e){
		System.out.println("budukh-rn-----29.05.2008-----"+e.getName() + " " + e.getSalary() + " -----e=" + e.getClass().getSimpleName());
	}
	
	public static void main(String[] args) {
		Example example = new Example();
		
		System.out.println("budukh-rn-----29.05.2008-----////////////////////---------------");
		
		Manager boss = new Manager("Boss", 25, 100.00 );
		boss.setBonus(1000);
		example.doSomsthing(boss);
		example.doSomsthing(new Employer("Chel1", 50, 50.00));
		example.doSomsthing(new Employer("Chel2", 55, 45.00));
		
	}
}
