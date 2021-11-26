package com.demo.developer.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.developer.Repository.StoriesRepository;
import com.demo.developer.model.Stories;

@Service
@Transactional
public class StoriesService {
	@Autowired
	private StoriesRepository repo;

	public void save(Stories stories) {
		repo.save(stories);
	}

	public Stories get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}

	public List<Stories> listAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	public List<Stories> findByNameEndsWith(String name) {

		return (List<Stories>) repo.findByNameEndsWith(name);

	}

	public int assignCount(String name) {

		return (Integer) repo.assignCount(name);

	}

	public int totalEstimation(String name) {
		try {

			return (Integer) repo.totalEstimation(name);

		} catch (Exception e) {
			return 0;
		}
	}

	public List<Stories> storiesList() {
		return (List<Stories>) repo.storiesList();
	}

	public Date maxDesCreationDate(String name) {
		try {

			return (Date) repo.maxDesCreationDate(name);

		} catch (Exception e) {
			return null;
		}
	}

	public Date minDesCreationDate(String name) {
		try {

			return (Date) repo.minDesCreationDate(name);

		} catch (Exception e) {
			return null;
		}
	}

	public int totalEstimationSet() {
		try {

			return (int) repo.totalEstimationSet();

		} catch (Exception e) {
			return 0;
		}
	}
}
