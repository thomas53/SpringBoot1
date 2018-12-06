package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.base.controller.message.BaseResponse;
import com.employee.message.AddEmployeeRequest;
import com.employee.message.AllEmployeeResponse;
import com.employee.message.DetailEmployeeResponse;
import com.employee.services.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value="/employee",method=RequestMethod.GET)
	public @ResponseBody AllEmployeeResponse callInquiryAllDataService() {
		AllEmployeeResponse response = employeeService.inquiryAllEmployeeService();
		return response;
	}

	@RequestMapping(value="/employee/{nik}",method=RequestMethod.GET)
	public @ResponseBody DetailEmployeeResponse callInquiryDetailEmployeeService(@PathVariable String nik) {
		DetailEmployeeResponse response = employeeService.inquiryDetailEmployeeService(nik);
		return response;
	}

	@RequestMapping(value="/employee",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BaseResponse callAddDataService(@RequestBody AddEmployeeRequest message) {
		BaseResponse response = employeeService.addNewEmployeeService(message);
		return response;
	}

	@RequestMapping(value="/employee/{nik}",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BaseResponse callUpdateEmployeeService(@PathVariable String nik, @RequestBody AddEmployeeRequest message) {
		BaseResponse response = employeeService.updateEmployeeService(message, nik);
		return response;
	}

	@RequestMapping(value="/employee/{nik}",method=RequestMethod.DELETE)
	public @ResponseBody BaseResponse callDeleteEmployeeService(@PathVariable String nik) {
		BaseResponse response = employeeService.deleteEmployeeService(nik);
		return response;
	}
	
}
