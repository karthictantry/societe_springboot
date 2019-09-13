package com.employee;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.beans.Employee;
import com.employee.validations.Validator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorTests {

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
	public void testFalseValidateEmployee() {
		Validator erc = new Validator();
		assertFalse(erc.validateEmployee(emp));
	}
	
	@Test
	public void testTrueValidateEmployee() {
		Validator erc = new Validator();
		emp.setFirstName(null);
		assertTrue(erc.validateEmployee(emp));
	}
	
	@Test
	public void testTrueCheckDuplicates() {
		Validator erc = new Validator();
		List<Employee> list = new ArrayList<Employee>();
		list.add(emp);
		assertTrue(erc.checkDuplicates(list, emp));
	}
	
	@Test
	public void testFalseCheckDuplicates() {
		Validator erc = new Validator();
		List<Employee> list = new ArrayList<Employee>();
		list.add(emp);
		emp.setFirstName(null);
		assertTrue(erc.checkDuplicates(list, emp));
	}
}
