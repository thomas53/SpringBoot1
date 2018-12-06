package com.employee.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.base.constant.ResponseCodeConstant;
import com.employee.base.controller.message.BaseResponse;
import com.employee.entity.Employee;
import com.employee.message.AddEmployeeRequest;
import com.employee.message.AllEmployeeResponse;
import com.employee.message.DetailEmployeeResponse;
import com.employee.message.EmployeeMessage;
import com.employee.repository.EmployeeRepository;
import com.employee.services.EmployeeService;

@Service
public class EmployeeServiceImpl extends BaseEmployeeService implements EmployeeService{

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public AllEmployeeResponse inquiryAllEmployeeService() {
		AllEmployeeResponse response = new AllEmployeeResponse();
		String responseCode = ResponseCodeConstant.DATA_NOT_FOUND;
		List<EmployeeMessage> employeeMessages = new ArrayList<EmployeeMessage>();
		
		List<Employee> employees = employeeRepository.findAll();
		
		
		if (employees !=null && !employees.isEmpty()) {
			responseCode = ResponseCodeConstant.SUCCESS;
			Double avg = employeeRepository.average();
			for (Employee employee : employees) {
				EmployeeMessage employeeMessage = new EmployeeMessage();
				employeeMessage.setName(employee.getName());
				employeeMessage.setNik(employee.getNik());
				employeeMessage.setPointNumber(employee.getPointNumber());
				employeeMessage.setPointAlphabet(generateAlphabetPoint(avg, employee.getPointNumber()));
				employeeMessages.add(employeeMessage);
			}
		}
		response.setResponseCode(responseCode);
		response.setResponseMessage(generateMessage(responseCode));
		response.setEmployees(employeeMessages);
		return response;
	}

	@Override
	public DetailEmployeeResponse inquiryDetailEmployeeService(String nik) {
		DetailEmployeeResponse response = new DetailEmployeeResponse();
		String responseCode = ResponseCodeConstant.DATA_NOT_FOUND;
		EmployeeMessage employeeMessage = null;
		
		Employee employee = employeeRepository.findByNik(nik);
		
		
		if (employee != null) {
			responseCode = ResponseCodeConstant.SUCCESS;
			Double avg = employeeRepository.average();
			employeeMessage = new EmployeeMessage();
			employeeMessage.setName(employee.getName());
			employeeMessage.setNik(employee.getNik());
			employeeMessage.setPointNumber(employee.getPointNumber());
			employeeMessage.setPointAlphabet(generateAlphabetPoint(avg, employee.getPointNumber()));
		}
		
		
		response.setResponseCode(responseCode);
		response.setResponseMessage(generateMessage(responseCode));
		response.setEmployee(employeeMessage);		
		return response;
	}

	@Override
	public BaseResponse addNewEmployeeService(AddEmployeeRequest message) {
		BaseResponse response = new BaseResponse();
		String responseCode = ResponseCodeConstant.ID_ALREADY_REGISTERED;
		Employee employee = employeeRepository.findByNik(message.getNik());
		if (employee==null) {
			responseCode = ResponseCodeConstant.SUCCESS;
			try {
				employee = new Employee();
				employee.setDateOfBirth(message.getDob());
				employee.setName(message.getName());
				employee.setNik(message.getNik());
				employee.setPointNumber(message.getPoint());
				
				employeeRepository.save(employee);
			} catch (Exception e) {
				responseCode = ResponseCodeConstant.ERROR_PROCESSING_TO_DATABASE;
				log.debug("error : " + e.getStackTrace());
			}
		}
		
		
		response.setResponseCode(responseCode);
		response.setResponseMessage(generateMessage(responseCode));
		return response;
	}

	@Override
	public BaseResponse updateEmployeeService(AddEmployeeRequest message, String nik) {
		BaseResponse response = new BaseResponse();
		String responseCode = ResponseCodeConstant.DATA_NOT_FOUND;
		Employee employee = employeeRepository.findByNik(nik);
		if (employee!=null) {
			responseCode = isNikRegisterd(message, nik);
			
			if (responseCode.equals(ResponseCodeConstant.SUCCESS)) {
				nik = isDataExist(message.getNik()) ? message.getNik() : employee.getNik();
				String name = isDataExist(message.getName()) ? message.getName() : employee.getName();
				Double pointNumber = isDataExist(message.getPoint()) ? message.getPoint() : employee.getPointNumber();
				Date dateOfBirth = isDataExist(message.getDob()) ? message.getDob() : employee.getDateOfBirth();
				
				try {
					employee.setDateOfBirth(dateOfBirth);
					employee.setName(name);
					employee.setNik(nik);
					employee.setPointNumber(pointNumber);
					
					employeeRepository.save(employee);
				} catch (Exception e) {
					responseCode = ResponseCodeConstant.ERROR_PROCESSING_TO_DATABASE;
					log.debug("error : " + e.getStackTrace());
				}
			}
			
		}
		response.setResponseCode(responseCode);
		response.setResponseMessage(generateMessage(responseCode));
		return response;
	}

	private String isNikRegisterd(AddEmployeeRequest message, String nik) {
		String responseCode;
		responseCode = ResponseCodeConstant.ID_ALREADY_REGISTERED;
		if (!nik.equals(message.getNik())) {
			Employee checkEmployee = employeeRepository.findByNik(message.getNik());
			if (checkEmployee==null) {
				responseCode = ResponseCodeConstant.SUCCESS;
			}
		}else {
			responseCode = ResponseCodeConstant.SUCCESS;
		}
		return responseCode;
	}


	

	@Override
	public BaseResponse deleteEmployeeService(String nik) {
		BaseResponse response = new BaseResponse();
		String responseCode = ResponseCodeConstant.DATA_NOT_FOUND;
		Employee employee = employeeRepository.findByNik(nik);
		if (employee!=null) {
			responseCode = ResponseCodeConstant.SUCCESS;
			
			try {
				employeeRepository.delete(employee);
			} catch (Exception e) {
				responseCode = ResponseCodeConstant.ERROR_PROCESSING_TO_DATABASE;
				log.debug("error : " + e.getStackTrace());
			}
		}
		response.setResponseCode(responseCode);
		response.setResponseMessage(generateMessage(responseCode));
		return response;
	}


	
}
