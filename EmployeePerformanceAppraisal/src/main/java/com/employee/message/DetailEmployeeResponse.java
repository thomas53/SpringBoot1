package com.employee.message;

import com.employee.base.controller.message.BaseResponse;

public class DetailEmployeeResponse extends BaseResponse{
	private EmployeeMessage employee;

	public EmployeeMessage getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeMessage employee) {
		this.employee = employee;
	}
	
	
}
