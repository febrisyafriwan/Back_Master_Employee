package com.demo.masterkaryawan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.masterkaryawan.model.Position;
import com.demo.masterkaryawan.repository.PositionRepository;

@Service
public class PositionDAOImplement implements PositionDAO {

	@Autowired
	private PositionRepository positionRepository;
	
	@Override
	public List<Position> getList() {
		// TODO Auto-generated method stub
		List<Position> positionRepo= positionRepository.allPosition();
		List<Position> list = new ArrayList<>();
		for (Position row : positionRepo) {
			Position temp = new Position();
			temp.setId(row.getId());
			temp.setCode(row.getCode());
			temp.setName(row.getName());
			temp.setIsDelete(row.getIsDelete());
			list.add(temp);
		}
		return list;
	}

}
