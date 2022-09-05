package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
