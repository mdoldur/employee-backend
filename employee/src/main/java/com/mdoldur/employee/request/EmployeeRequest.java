package com.mdoldur.employee.request;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EmployeeRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	Long id;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	String name;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	String surname;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	Date startDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
}
