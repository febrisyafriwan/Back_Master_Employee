package com.demo.masterkaryawan.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.masterkaryawan.dto.EmployeeDTO;
import com.demo.masterkaryawan.helper.CommonResponse;
import com.demo.masterkaryawan.helper.EmployeeRequest;
import com.demo.masterkaryawan.helper.NotFoundException;
import com.demo.masterkaryawan.model.Employee;
import com.demo.masterkaryawan.model.Position;
import com.demo.masterkaryawan.service.EmployeeDAO;
import com.demo.masterkaryawan.service.PositionDAO;


@CrossOrigin
@RestController
public class EmployeeController {
	@Autowired
	private EmployeeDAO employeeDao;
	
	@Autowired
	private PositionDAO positionDao;
	
	@GetMapping("/employees")
	public CommonResponse<Page<Employee>> index (Pageable pageable) {
		Page<Employee> employeeList  = employeeDao.getListForPagination(pageable);
		CommonResponse<Page<Employee>> response = new CommonResponse<>();
		response.setstatus(200);
		response.setMessage("success");
		response.setData(employeeList);
		return response;
	}

	@PostMapping("/employeeAdd")
	public CommonResponse<EmployeeRequest> insert(@RequestBody EmployeeRequest employeeRequest) {
		EmployeeRequest employee = employeeDao.insert(employeeRequest);
		CommonResponse<EmployeeRequest> response = new CommonResponse<>();
		response.setstatus(200);
		response.setMessage("success");
		response.setData(employee);
		return response;
	}

	@PostMapping("/employeeEdit")
	public CommonResponse<EmployeeRequest> update(@RequestBody EmployeeRequest employeeRequest) throws NotFoundException,Exception  {
		EmployeeRequest employee = employeeDao.update(employeeRequest);
		if(employee == null) {
			throw new NotFoundException(404,"id not found");
		}else {
			CommonResponse<EmployeeRequest> response = new CommonResponse<>();
			response.setstatus(200);
			response.setMessage("success");
			response.setData(employee);
			return response;
		}
	}
	
	@PostMapping("/employeeDelete")
	public CommonResponse<EmployeeRequest> delete( @RequestBody EmployeeRequest employeeRequest) throws NotFoundException,Exception {
		EmployeeRequest employee = employeeDao.delete(employeeRequest);
		if(employee == null) {
			throw new NotFoundException(404,"id not found");
		}else {
			CommonResponse<EmployeeRequest> response = new CommonResponse<>();
			response.setstatus(200);
			response.setMessage("success");
			response.setData(employee);
			return response;
		}
		
	}
	
	@GetMapping("/employeePosition/{id}")
	public CommonResponse<EmployeeDTO> addEdit(@PathVariable (value = "id") int id) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		List<Position> positionList = positionDao.getList();
		employeeDTO.setPositionList(positionList);
		Employee employee = employeeDao.getById(id);
		if(employee != null) {
			employeeDTO.setEmployee(employee);
		}
		CommonResponse<EmployeeDTO> response = new CommonResponse<>();
		response.setstatus(200);
		response.setMessage("success");
		response.setData(employeeDTO);
		return response;
	}
}
