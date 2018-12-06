package com.employee.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="employee")
public class Employee {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(unique=true)
	@NotNull
	private String nik;
	private String name;
	@NotNull
	private Double pointNumber;
	private Date dateOfBirth;
	
	public Employee() {}

	public Employee(String nik, String name, Double pointNumber, Date dateOfBirth) {
		this.nik = nik;
		this.name = name;
		this.pointNumber = pointNumber;
		this.dateOfBirth = dateOfBirth;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		if (nik!=null && !nik.equals("")) {
			this.nik = nik;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name!=null && !name.equals("")) {
			this.name = name;
		}
	}

	public Double getPointNumber() {
		return pointNumber;
	}

	public void setPointNumber(Double pointNumber) {
		if (pointNumber!=null) {
			this.pointNumber = pointNumber;
		}
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		if (dateOfBirth!=null) {
			this.dateOfBirth = dateOfBirth;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
