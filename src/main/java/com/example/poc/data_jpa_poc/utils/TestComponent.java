package com.example.poc.data_jpa_poc.utils;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {

  @PreDestroy
  private void test() {
    System.out.println("test destroy");
  }
}
