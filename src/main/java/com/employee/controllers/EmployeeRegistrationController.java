package com.employee.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.beans.Employee;
import com.employee.constants.CommonConstants;
import com.employee.validations.Validator;

@RestController
public class EmployeeRegistrationController {
	
	private List<Employee> employeeList = new ArrayList<Employee>();
	
	@GetMapping(path = "employees", produces = "application/json")
	public List<Employee> getEmployees() {
		System.out.print("In Get");
		return employeeList;
	}
	
	@PostMapping(path = "employees/register", consumes = "application/json", produces = "application/json")
	public Map<String, String> addEmployee(@RequestBody Employee employee) {
		Map<String, String> responseObj = new HashMap<String, String>();
		Validator validator = new Validator();
		if (validator.validateEmployee(employee)) {
			if (!validator.checkDuplicates(employeeList, employee)) {
				employeeList.add(employee);
				responseObj.put(CommonConstants.KEY_REPONSE_CODE, CommonConstants.SUCCESS_REPONSE_CODE);
				responseObj.put(CommonConstants.KEY_RESPONSE_MESSAGE, CommonConstants.SUCCESS_RESPONSE_MESSAGE);
				return responseObj;
			}
		}
		responseObj.put(CommonConstants.KEY_REPONSE_CODE, CommonConstants.FAILURE_REPONSE_CODE);
		responseObj.put(CommonConstants.KEY_RESPONSE_MESSAGE, CommonConstants.FAILURE_RESPONSE_MESSAGE);
		return responseObj;
	}
}
