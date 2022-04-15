package com.mdoldur.employee.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mdoldur.employee.entity.Employee;

public final class EmployeeDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	String name;
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	String surname;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	Date startDate;
	
	private EmployeeDTO(String name, String surname, Date startDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.startDate = startDate;
	}

	public static EmployeeDTO create(Employee employee) {
		return new EmployeeDTO(employee.getName(), employee.getSurname(), employee.getStartDate());
	}

}
