package com.mdoldur.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdoldur.employee.request.EmployeeRequest;
import com.mdoldur.employee.response.EmployeeDTO;
import com.mdoldur.employee.service.interfaces.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(value="pageNo", defaultValue = "0", required = false) Integer pageNo,
															 @RequestParam(value="pageSize", defaultValue = "1", required = false) Integer pageSize) {
		return employeeService.getAllEmployees(pageNo, pageSize);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long id) {
		return employeeService.getEmployee(id);
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createEmployee(@RequestBody EmployeeRequest employee) {
		return employeeService.createEmployee(employee);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateEmployee(@RequestBody EmployeeRequest employee) {
		return employeeService.updateEmployee(employee);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		return employeeService.delete(id);
	}
	
}
