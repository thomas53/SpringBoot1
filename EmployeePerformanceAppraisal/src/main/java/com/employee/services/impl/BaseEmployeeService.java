package com.employee.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@PropertySource("classpath:message.properties")
public class BaseEmployeeService {
	
	@Autowired
	private Environment env;
	
	
	protected String generateMessage(String responseCode) {
		String message = env.getProperty("message.error.code." + responseCode);
		
		if (message==null || message.equals("")) {
			message = "Pesan Error Tidak Diketahui.";
		}
		
		return message;
	}
	
	protected String generateAlphabetPoint(Double avg, Double point) {
		Double percent = 0.0;
		Double different = point - avg;
		
		String grade = "";
		if (different > 0) {
			percent = different/avg*100;
			if (percent > 20.0) {
				grade = "A";
			}else {
				grade = "B";
			}
		}else {
			percent = Math.abs(different)/avg*100;
			if (percent <= 10.0) {
				grade = "C";
			}else if(percent > 10 && percent<=20){
				grade = "D";
			}else {
				grade = "E";
			}
		}
		return grade;
	}
	
	protected boolean isDataExist(Object obj) {
		if (obj!=null) {
			if (obj.getClass().equals(String.class)) {
				if (!obj.toString().equals("")) {
					return true;
				}else {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
