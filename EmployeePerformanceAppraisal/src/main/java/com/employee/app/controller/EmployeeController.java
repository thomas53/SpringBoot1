package com.employee.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.app.base.controller.message.BaseResponse;
import com.employee.app.message.AddEmployeeRequest;
import com.employee.app.message.AllEmployeeResponse;
import com.employee.app.message.DetailEmployeeResponse;
import com.employee.app.services.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping(value="/employee")
	public @ResponseBody AllEmployeeResponse callInquiryAllDataService() {
		return employeeService.inquiryAllEmployeeService();
	}

	@GetMapping(value="/employee/{nik}")
	public @ResponseBody DetailEmployeeResponse callInquiryDetailEmployeeService(@PathVariable String nik) {
		return employeeService.inquiryDetailEmployeeService(nik);
	}

	@PostMapping(value="/employee",consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BaseResponse callAddDataService(@RequestBody AddEmployeeRequest message) {		
		return employeeService.addNewEmployeeService(message);
	}

	@PutMapping(value="/employee/{nik}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody BaseResponse callUpdateEmployeeService(@PathVariable String nik, @RequestBody AddEmployeeRequest message) {
		return employeeService.updateEmployeeService(message, nik);
	}

	@DeleteMapping(value="/employee/{nik}")
	public @ResponseBody BaseResponse callDeleteEmployeeService(@PathVariable String nik) {
		return employeeService.deleteEmployeeService(nik);
	}

}
