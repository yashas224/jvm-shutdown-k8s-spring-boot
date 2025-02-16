package com.example.poc.data_jpa_poc.controller;

import com.example.poc.data_jpa_poc.entity.Person;
import com.example.poc.data_jpa_poc.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/poc-app")
public class AppController {

  @Autowired
  PersonRepository personRepository;

  @Value("${MY_POD_NAME}")
  private String podName;

  @Autowired
  ObjectMapper objectMapper;

  @GetMapping("/{name}")
  public ResponseEntity<?> checkUser(@PathVariable(name = "name") String name) {
    boolean found = personRepository.existsByNameLikeIgnoreCase(name);
    log.info("APP LOG user found: {}", found ? "YES" : "NO");
    return found ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
  }

  @PostMapping("/")
  public ResponseEntity<?> saveUser(@RequestBody String body) throws JsonProcessingException {
    JsonNode node = objectMapper.readTree(body);
    var name = node.get("name").asText();
    var person = new Person();
    person.setName(name);
    personRepository.save(person);
    log.info("user saved: {}", person);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostConstruct
  private void initController() {
    log.info("{} initController", podName);
    // init shutDownHook
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      log.info("{} addShutdownHook", podName);
      var person = new Person();
      person.setName(podName.concat("- addShutdownHook"));
      personRepository.save(person);
    }));
  }

  @PreDestroy
  private void cleanUpController() {
    log.info("{} cleanUpController", podName);
    var person = new Person();
    person.setName(podName.concat("-cleanUpController"));
    personRepository.save(person);
  }
}
