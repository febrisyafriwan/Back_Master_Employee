package com.demo.masterkaryawan.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.demo.masterkaryawan.helper.EmployeeRequest;
import com.demo.masterkaryawan.model.Employee;

public interface EmployeeDAO {
	
	public Page<Employee> getListForPagination (Pageable pageable);
	public Employee getById (int id);
	public EmployeeRequest insert(EmployeeRequest employee);
	public EmployeeRequest update(EmployeeRequest employee);
	public EmployeeRequest delete(EmployeeRequest employee);
}
