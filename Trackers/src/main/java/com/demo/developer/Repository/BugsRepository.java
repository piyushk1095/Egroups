package com.demo.developer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.developer.model.Bugs;

public interface BugsRepository extends JpaRepository<Bugs, Integer> {

}