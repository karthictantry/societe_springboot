package com.employee;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.beans.Employee;
import com.employee.constants.CommonConstants;
import com.employee.controllers.EmployeeRegistrationController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeApplicationTests {

	Employee emp = new Employee();
	
	@Before
	public void before() {
		emp.setFirstName("Ravi");
		emp.setLastName("Kiran");
		emp.setGender("M");
		emp.setDateOfBirth("25-December-1990");
		emp.setDepartmentName("Boarding");
	}
	   
	@Test
	public void testAddEmployee() {
		EmployeeRegistrationController erc = new EmployeeRegistrationController();
		Map<String, String> response = new HashMap<String, String>();
		response.put(CommonConstants.KEY_REPONSE_CODE, CommonConstants.SUCCESS_REPONSE_CODE);
		response.put(CommonConstants.KEY_RESPONSE_MESSAGE, CommonConstants.SUCCESS_RESPONSE_MESSAGE);
		assertEquals(response, erc.addEmployee(emp));
	}
	
	@Test
	public void testFailAddEmployee() {
		EmployeeRegistrationController erc = new EmployeeRegistrationController();
		emp.setGender(null); // Set a field to null so we get the failure message.
		Map<String, String> response = new HashMap<String, String>();
		response.put(CommonConstants.KEY_REPONSE_CODE, CommonConstants.FAILURE_REPONSE_CODE);
		response.put(CommonConstants.KEY_RESPONSE_MESSAGE, CommonConstants.FAILURE_RESPONSE_MESSAGE);
		assertEquals(response, erc.addEmployee(emp));
	}
	
	@Test
	public void testGetEmployees() {
		EmployeeRegistrationController erc = new EmployeeRegistrationController();
		List<Employee> list = new ArrayList<Employee>();
		erc.addEmployee(emp);
		list.add(emp);
		assertEquals(list, erc.getEmployees());
	}

}
