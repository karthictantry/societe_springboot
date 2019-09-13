package com.employee.validations;

import java.util.List;

import com.employee.beans.Employee;

public class Validator {
	
	public boolean validateEmployee(Employee employee) {
		boolean validFlag = true;
		if (checkEmpty(employee.getFirstName()) || checkEmpty(employee.getLastName()) || checkEmpty(employee.getGender())
				|| checkEmpty(employee.getDateOfBirth()) || checkEmpty(employee.getDepartmentName())) {
			validFlag = false;
		}
		return validFlag;
	}
	
	private boolean checkEmpty(String value) {
		if (value == null || value == "") {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkDuplicates(List<Employee> employeeList, Employee employee) {
		boolean duplicateFlag = false;
		for (Employee emp : employeeList) {
			if (employee.equals(emp)) {
				duplicateFlag = true;
				break;
			}
		}
		return duplicateFlag;
	}
}
