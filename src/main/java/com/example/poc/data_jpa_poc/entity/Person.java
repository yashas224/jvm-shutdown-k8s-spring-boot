package com.example.poc.data_jpa_poc.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  private String name;
}

