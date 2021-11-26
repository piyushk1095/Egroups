package com.demo.developer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.developer.Repository.DeveloperRepository;
import com.demo.developer.model.Developer;

@Service
@Transactional
public class DeveloperService {
	@Autowired
	private DeveloperRepository repo;

	public void save(Developer developer) {
		repo.save(developer);
	}

	public Developer get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}

	public List<Developer> listAll() {

		return repo.findAll();
	}

	public List<Developer> devSet() {

		return (List<Developer>) repo.devSet();

	}

}
