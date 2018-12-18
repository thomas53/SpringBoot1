package com.employee.app.message;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class EmployeeMessage {
	private String nik;
	private String name;
	private Double pointNumber;
	private String pointAlphabet;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dateOfBirth;

	public EmployeeMessage() {}

	public EmployeeMessage(String nik, String name, Double pointNumber, String pointAlphabet, Date dateOfBirth) {
		this.nik = nik;
		this.name = name;
		this.pointNumber = pointNumber;
		this.pointAlphabet = pointAlphabet;
		this.dateOfBirth = dateOfBirth;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPointNumber() {
		return pointNumber;
	}

	public void setPointNumber(Double pointNumber) {
		this.pointNumber = pointNumber;
	}

	public String getPointAlphabet() {
		return pointAlphabet;
	}

	public void setPointAlphabet(String pointAlphabet) {
		this.pointAlphabet = pointAlphabet;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	
	
	
}
