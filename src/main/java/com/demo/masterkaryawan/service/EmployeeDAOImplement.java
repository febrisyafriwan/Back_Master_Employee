package com.demo.masterkaryawan.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.masterkaryawan.helper.EmployeeRequest;
import com.demo.masterkaryawan.model.Employee;
import com.demo.masterkaryawan.repository.EmployeeRepository;
@Service
public class EmployeeDAOImplement implements EmployeeDAO {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Page<Employee> getListForPagination(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Employee> employeeRepo  = employeeRepository.allEmployee(pageable);
		return employeeRepo;
	}

	@Override
	public Employee getById(int id) {
		// TODO Auto-generated method stub
		boolean isCheck = employeeRepository.existsById(id);
		if(isCheck) {
			Employee employee = employeeRepository.employeeById(id);	
			return employee;
		}else {
			return null;
		}
	}

	@Override
	public EmployeeRequest insert(EmployeeRequest employeeRequest) {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(employeeRequest.getBirthDate());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Employee employee = new Employee();
		employee.setName(employeeRequest.getName());
		employee.setBirthDate(date);
		employee.setPosition(employeeRequest.getPosition());
		employee.setIdNumber(employeeRequest.getIdNumber());
		employee.setGender(employeeRequest.getGender());
		employee.setIsDelete(employeeRequest.getIsDelete());
		employeeRepository.save(employee);
		return employeeRequest;
	}

	@Override
	public EmployeeRequest update(EmployeeRequest employeeRequest) {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = formatter.parse(employeeRequest.getBirthDate());

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean isCheck = employeeRepository.existsById(employeeRequest.getId());
		if (isCheck) {
			employeeRepository.updateEmployee(employeeRequest.getName(), date, employeeRequest.getPosition(),employeeRequest.getIdNumber(), employeeRequest.getGender(), 
			employeeRequest.getIsDelete(), employeeRequest.getId());
			return employeeRequest;
		}else {
			return null;	
		}
	}
	@Override
	public EmployeeRequest delete(EmployeeRequest employeeRequest) {
		// TODO Auto-generated method stub
		boolean isCheck = employeeRepository.existsById(employeeRequest.getId());
		if (isCheck) {
			employeeRepository.deleteEmployee(employeeRequest.getId());;
			return employeeRequest;
		}else {
			return null;	
		}
	}
}
