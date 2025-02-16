package com.example.poc.data_jpa_poc.repository;

import com.example.poc.data_jpa_poc.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Long> {
  @Query("select (count(p) > 0) from Person p where upper(p.name) like upper(?1)")
  boolean existsByNameLikeIgnoreCase(String name);


}
