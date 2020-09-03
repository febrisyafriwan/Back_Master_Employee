package com.demo.masterkaryawan.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.demo.masterkaryawan.model.Position;


@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

	@Transactional
	@Query(value = "Select a from Position a ")
	List<Position> allPosition();
}
