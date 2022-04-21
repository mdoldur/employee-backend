package com.mdoldur.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mdoldur.employee.entity.Employee;
import com.mdoldur.employee.repository.EmployeeRepository;
import com.mdoldur.employee.request.EmployeeRequest;
import com.mdoldur.employee.response.EmployeeDTO;
import com.mdoldur.employee.service.interfaces.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public ResponseEntity<String> createEmployee(EmployeeRequest employee) {
		Employee newEmp = new Employee(employee.getName(), employee.getSurname(), employee.getStartDate());
		employeeRepository.save(newEmp);
		return new ResponseEntity<>("Employee Created", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<EmployeeDTO> getEmployee(Long id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		if (emp.isPresent()) {
			return new ResponseEntity<>(EmployeeDTO.create(emp.get()), HttpStatus.OK);
		}
		
		Employee empty = new Employee();
		empty.setName("Employee Not Found");
		return new ResponseEntity<>(EmployeeDTO.create(empty), HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(Integer pageNo, Integer pageSize) {
		List<Employee> all = null;
		
		if (pageNo != null && pageSize != null && pageSize.longValue() > 0) {
			Pageable pageable = PageRequest.of(pageNo, pageSize);
			Page<Employee> employees = employeeRepository.findAll(pageable);
			
			if (!employees.hasContent()) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			all = employees.getContent();
			
		} else {
			all = employeeRepository.findAll();
			if (all.size() == 0) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			/*all.stream()
				 .forEach(item -> {
					EmployeeDTO newItem = EmployeeDTO.create(item);
					result.add(newItem);
									});*/
		}
		
		List<EmployeeDTO> result = all.stream()
						              .map(EmployeeDTO::create)
						              .collect(Collectors.toList());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> updateEmployee(EmployeeRequest employee) {
		Optional<Employee> emp = employeeRepository.findById(employee.getId());
		if (emp.isPresent()) {
			emp.get().setName(employee.getName());
			emp.get().setSurname(employee.getSurname());
			emp.get().setStartDate(employee.getStartDate());
			employeeRepository.save(emp.get());
			return new ResponseEntity<>("Employee Updated", HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Employee Not Found", HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<String> delete(Long id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		if (emp.isPresent()) {
			employeeRepository.delete(emp.get());
			return new ResponseEntity<>("Employee Deleted", HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Employee Not Found", HttpStatus.NOT_FOUND);
	}
	
}
