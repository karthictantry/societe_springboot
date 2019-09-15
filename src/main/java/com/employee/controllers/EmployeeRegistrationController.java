package com.employee.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.beans.Employee;
import com.employee.constants.CommonConstants;
import com.employee.validations.Validator;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmployeeRegistrationController {
	
	private List<Employee> employeeList = new ArrayList<Employee>();
	
	@GetMapping(path = "employees", produces = "application/json")
	public ResponseEntity<?> getEmployees() {
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Access-Control-Allow-Origin", "*");
	    headers.add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		return new ResponseEntity<>(employeeList,headers, HttpStatus.OK);
	}
	
	@PostMapping(path = "employees/register", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		Map<String, String> responseObj = new HashMap<String, String>();
		Validator validator = new Validator();
		HttpHeaders headers = new HttpHeaders();
		if (validator.validateEmployee(employee)) {
			if (!validator.checkDuplicates(employeeList, employee)) {
				employeeList.add(employee);
				responseObj.put(CommonConstants.KEY_REPONSE_CODE, CommonConstants.SUCCESS_REPONSE_CODE);
				responseObj.put(CommonConstants.KEY_RESPONSE_MESSAGE, CommonConstants.SUCCESS_RESPONSE_MESSAGE);
				headers.add("Access-Control-Allow-Origin", "*");
			    headers.add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
			    headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
			    headers.add("Access-Control-Allow-Credentials", "true");
			    return new ResponseEntity<>(responseObj, headers,HttpStatus.CREATED);
			}
		}
		responseObj.put(CommonConstants.KEY_REPONSE_CODE, CommonConstants.FAILURE_REPONSE_CODE);
		responseObj.put(CommonConstants.KEY_RESPONSE_MESSAGE, CommonConstants.FAILURE_RESPONSE_MESSAGE);
	    headers.add("Access-Control-Allow-Origin", "*");
	    headers.add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	    headers.add("Access-Control-Allow-Headers", "X-Requested-With,content-type");
	    headers.add("Access-Control-Allow-Credentials", "true");
	    return new ResponseEntity<>(responseObj, headers,HttpStatus.CREATED);
	}
}
