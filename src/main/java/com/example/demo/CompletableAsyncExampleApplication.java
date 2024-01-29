package com.example.demo;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import data.EmployeeData;
import dto.Employee;


public class CompletableAsyncExampleApplication {
	
	public List<Employee> getEmployees() throws InterruptedException, ExecutionException
	{
		
		Executor executor = Executors.newFixedThreadPool(5);
		CompletableFuture<List<Employee>> completableList = CompletableFuture.supplyAsync(() -> {
			
			System.out.println("Fetch All Employees : " + Thread.currentThread().getName());
			return EmployeeData.fetchEmployee();
			
		}, executor).thenApplyAsync((employees) -> {
			System.out.println("Filter Female employees : "+ Thread.currentThread().getName());
			
			return employees.stream().filter(emp -> emp.getGender().equalsIgnoreCase("Female")).collect(Collectors.toList());
		}, executor).thenApplyAsync((employees) -> {
			System.out.println("Filter Status as true for female employees : "+ Thread.currentThread().getName());
			return employees.stream().filter(empObj -> empObj.isStatus()).collect(Collectors.toList());
		}, executor);
	
			return completableList.get();
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		 
		CompletableAsyncExampleApplication supplyAsyn = new CompletableAsyncExampleApplication();
		List<Employee> employees = supplyAsyn.getEmployees();
		System.out.println("Final List Size = "+ employees.size());
		employees.stream().forEach(obj -> System.out.println(obj.getId()+"-"+obj.getName()));
		
		
	}
}
