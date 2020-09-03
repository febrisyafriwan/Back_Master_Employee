package com.demo.masterkaryawan.dto;

import java.util.List;
import com.demo.masterkaryawan.model.Employee;
import com.demo.masterkaryawan.model.Position;

public class EmployeeDTO {

	private List<Position> positionList;
	private Employee employee;
	
	public List<Position> getPositionList() {
		return positionList;
	}
	public void setPositionList(List<Position> positionList) {
		this.positionList = positionList;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
