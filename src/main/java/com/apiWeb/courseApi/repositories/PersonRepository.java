package com.apiWeb.courseApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apiWeb.courseApi.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
