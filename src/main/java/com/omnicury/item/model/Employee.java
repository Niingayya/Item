package com.omnicury.item.model;

public class Employee {
	
	private int id;
	private String name;
	private double salary;
	private int managerId;
	
    Employee(int id,String name,double salary,int managerId){
    	this.id=id;
    	this.name=name;
    	this.salary=salary;
    	this.managerId=managerId;
		
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

    
}
