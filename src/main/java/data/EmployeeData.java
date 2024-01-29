package data;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.Employee;

public class EmployeeData {
	
 public static List<Employee> fetchEmployee()
 {
	 ObjectMapper mapper = new ObjectMapper();
	 try
	 {
		 return mapper.readValue(new File("EmployeeDetails.json"), new TypeReference<List<Employee>>() {			 
		 });
	 } catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 return null;
	 
 }
}
