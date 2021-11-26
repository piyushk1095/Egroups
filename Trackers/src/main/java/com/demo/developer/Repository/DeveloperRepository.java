package com.demo.developer.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.developer.model.Developer;

public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

	@Query("select c from Developer c")
	List<Developer> devSet();
}