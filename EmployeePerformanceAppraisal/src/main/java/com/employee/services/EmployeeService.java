package com.employee.services;

import com.employee.base.controller.message.BaseResponse;
import com.employee.message.AddEmployeeRequest;
import com.employee.message.AllEmployeeResponse;
import com.employee.message.DetailEmployeeResponse;

public interface EmployeeService {
	public AllEmployeeResponse inquiryAllEmployeeService();
	public DetailEmployeeResponse inquiryDetailEmployeeService(String nik);
	public BaseResponse addNewEmployeeService(AddEmployeeRequest message);
	public BaseResponse updateEmployeeService(AddEmployeeRequest message,String nik);
	public BaseResponse deleteEmployeeService(String nik);	
}
