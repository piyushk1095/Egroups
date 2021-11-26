package com.demo.developer.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.developer.model.Stories;

public interface StoriesRepository extends JpaRepository<Stories, Integer> {

	@Query("select c from Stories c where c.assignedDev= ?1")
	List<Stories> findByNameEndsWith(String asignDev);

	@Query("select count(c) from Stories c where c.assignedDev= ?1")
	int assignCount(String asignDev);

	@Query("select c from Stories c")
	List<Stories> storiesList();

	@Query("select sum(c.estimatedPoints) from Stories c where c.assignedDev= ?1")
	int totalEstimation(String asignDev);

	@Query("select max(c.creationDate) from Stories c where c.assignedDev= ?1")
	Date maxDesCreationDate(String asignDev);

	@Query("select min(c.creationDate) from Stories c where c.assignedDev= ?1")
	Date minDesCreationDate(String asignDev);

	@Query("select sum(c.estimatedPoints) from Stories c")
	int totalEstimationSet();
}