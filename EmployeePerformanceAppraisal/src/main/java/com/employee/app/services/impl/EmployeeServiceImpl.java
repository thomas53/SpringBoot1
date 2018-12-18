package com.employee.app.services.impl;

import com.employee.app.base.Utils;
import com.employee.app.base.constant.ResponseCodeConstant;
import com.employee.app.base.service.BaseServiceImpl;
import com.employee.app.repository.EmployeeRepository;
import com.employee.app.services.EmployeeService;
import com.employee.app.base.controller.message.BaseResponse;
import com.employee.app.entity.Employee;
import com.employee.app.message.AddEmployeeRequest;
import com.employee.app.message.AllEmployeeResponse;
import com.employee.app.message.DetailEmployeeResponse;
import com.employee.app.message.EmployeeMessage;
import org.hibernate.JDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public AllEmployeeResponse inquiryAllEmployeeService() {
		AllEmployeeResponse response = new AllEmployeeResponse();
		String responseCode = ResponseCodeConstant.DATA_NOT_FOUND;
		List<EmployeeMessage> employeeMessages = new ArrayList<>();
		
		List<Employee> employees = employeeRepository.findAll();
		
		
		if (employees !=null && !employees.isEmpty()) {
			responseCode = ResponseCodeConstant.SUCCESS;
			Double avg = employeeRepository.average();

			for (Employee employee : employees) {
				EmployeeMessage employeeMessage = new EmployeeMessage();
				employeeMessage.setName(employee.getName());
				employeeMessage.setNik(employee.getNik());
				employeeMessage.setPointNumber(employee.getPointNumber());
				employeeMessage.setDateOfBirth(employee.getDateOfBirth());
				employeeMessage.setPointAlphabet(Utils.generateAlphabetPoint(avg, employee.getPointNumber()));
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
			employeeMessage.setDateOfBirth(employee.getDateOfBirth());
			employeeMessage.setPointNumber(employee.getPointNumber());
			employeeMessage.setPointAlphabet(Utils.generateAlphabetPoint(avg, employee.getPointNumber()));
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
				employee.setId(10);
				employeeRepository.save(employee);
			} catch (DataAccessException e) {
				responseCode = ResponseCodeConstant.ERROR_PROCESSING_TO_DATABASE;
				log.debug(e.getMessage());
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
				
				try {
					employee.setNik(message.getNik());
					employee.setName(message.getName());						
					employee.setPointNumber(message.getPoint());
					employee.setDateOfBirth(message.getDob());
					
					employeeRepository.save(employee);
				} catch (JDBCException e) {
					responseCode = ResponseCodeConstant.ERROR_PROCESSING_TO_DATABASE;
					log.debug(e.getMessage());
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
			} catch (JDBCException e) {
				responseCode = ResponseCodeConstant.ERROR_PROCESSING_TO_DATABASE;
				log.debug(e.getMessage());
			}
		}
		response.setResponseCode(responseCode);
		response.setResponseMessage(generateMessage(responseCode));
		return response;
	}


	
}
