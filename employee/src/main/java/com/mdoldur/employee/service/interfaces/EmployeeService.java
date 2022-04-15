package com.mdoldur.employee.service.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mdoldur.employee.request.EmployeeRequest;
import com.mdoldur.employee.response.EmployeeDTO;

public interface EmployeeService {
	
	ResponseEntity<List<EmployeeDTO>> getAllEmployees(Integer pageNo, Integer pageSize);

	ResponseEntity<String> createEmployee(EmployeeRequest employee);

	ResponseEntity<EmployeeDTO> getEmployee(Long id);

	ResponseEntity<String> updateEmployee(EmployeeRequest employee);

	ResponseEntity<String> delete(Long id);
	
}
