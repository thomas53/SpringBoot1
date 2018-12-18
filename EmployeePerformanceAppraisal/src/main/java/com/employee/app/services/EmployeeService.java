package com.employee.app.services;

import com.employee.app.base.controller.message.BaseResponse;
import com.employee.app.message.AddEmployeeRequest;
import com.employee.app.message.AllEmployeeResponse;
import com.employee.app.message.DetailEmployeeResponse;

public interface EmployeeService {
	AllEmployeeResponse inquiryAllEmployeeService();
	DetailEmployeeResponse inquiryDetailEmployeeService(String nik);
	BaseResponse addNewEmployeeService(AddEmployeeRequest message);
	BaseResponse updateEmployeeService(AddEmployeeRequest message,String nik);
	BaseResponse deleteEmployeeService(String nik);
}
