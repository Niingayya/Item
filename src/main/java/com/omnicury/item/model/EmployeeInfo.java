package com.omnicury.item.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeInfo {
	
	public static void main(String[] args){
		
	}
	
    List<Employee> employees=new ArrayList<Employee>();
	
	public Employee createEmployee(){
	Employee employee=new Employee(1,"A",20000.0,11);
	System.out.println("Employee id="+ employee.getId());
	System.out.println("Employee name ="+employee.getName());
	System.out.println("employee salary="+ employee.getSalary());
	System.out.println("employee salary="+ employee.getManagerId());
	Employee employee1=new Employee(2,"A",20000.0,11);
	employees.add(employee);
	employees.add(employee1);
	return employee;
	}

	public void getEmployee(){
		
		for (Employee employeee : employees) {
		System.out.println("Employee id="+ employeee.getId());
		System.out.println("Employee name ="+employeee.getName());
		System.out.println("employee salary="+ employeee.getSalary());
		System.out.println("employee salary="+ employeee.getManagerId());
	}
}
}	