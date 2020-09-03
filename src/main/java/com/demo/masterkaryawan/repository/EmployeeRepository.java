package com.demo.masterkaryawan.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.masterkaryawan.model.Employee;
import com.demo.masterkaryawan.model.Position;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Page<Employee> findByPositionId(Integer positionId, Pageable pageable);
	
	@Transactional
	@Query(value = "Select a from Employee a where a.id =:id")
	Employee employeeById(@Param(value = "id") int id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Employee SET name =:name, birth_date =:birth_date, position =:position, id_number =:id_number, gender =:gender, is_delete = :is_delete WHERE id =:id")
	void updateEmployee(@Param("name") String name, @Param("birth_date") Date birth_date, @Param("position") Position position, @Param("id_number") int id_number, @Param("gender") int gender, 
			@Param("is_delete") int is_delete, @Param("id") int id) ;

	@Transactional
	@Modifying
	@Query(value = "UPDATE Employee SET is_delete = 1 WHERE id =:id")
	void deleteEmployee(@Param("id") int id) ;

	
	@Transactional
	@Query("Select a from Employee a")
	public Page<Employee> allEmployee(Pageable pageable);
}
