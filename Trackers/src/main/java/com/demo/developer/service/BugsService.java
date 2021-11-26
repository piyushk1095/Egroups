package com.demo.developer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.developer.Repository.BugsRepository;
import com.demo.developer.model.Bugs;

@Service
@Transactional
public class BugsService {

	@Autowired
	private BugsRepository repo;

	public void save(Bugs bugs) {
		repo.save(bugs);
	}

	public Bugs get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}

	public List<Bugs> listAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
