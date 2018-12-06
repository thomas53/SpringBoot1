package com.employee.base.controller.message;

public class BaseResponse {
	private String responseCode;
	private String responseMessage;
	
	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String message) {
		this.responseMessage = message;
	}
	
}
