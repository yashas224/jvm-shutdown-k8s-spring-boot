package com.example.poc.data_jpa_poc.utils;

import com.example.poc.data_jpa_poc.entity.Person;
import com.example.poc.data_jpa_poc.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {

  @Autowired
  PersonRepository personRepository;

  @Value("${MY_POD_NAME}")
  private String podName;

  @Override
  public void run(String... args) {
    var person = new Person();
    person.setName(podName);
    personRepository.save(person);
  }
}