package com.employee.app.message;

import java.util.Date;

public class AddEmployeeRequest {
	
	private String nik;
	private String name;
	private Date dob;
	private Double point;

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date ttl) {
		this.dob = ttl;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}
	
	
}
