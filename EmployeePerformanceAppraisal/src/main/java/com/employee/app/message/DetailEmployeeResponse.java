package com.employee.app.message;

import com.employee.app.base.controller.message.BaseResponse;

public class DetailEmployeeResponse extends BaseResponse{
	private EmployeeMessage employee;

	public EmployeeMessage getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeMessage employee) {
		this.employee = employee;
	}
	
	
}
