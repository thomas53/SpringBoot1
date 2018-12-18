package com.employee.app.message;

import java.util.List;

import com.employee.app.base.controller.message.BaseResponse;

public class AllEmployeeResponse extends BaseResponse{
	private List<EmployeeMessage> employees;

	public List<EmployeeMessage> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeMessage> employees) {
		this.employees = employees;
	}
	
	
}
