package com.example.poc.data_jpa_poc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConnectionConfig {

  @Value("${mysql-host}")
  private String mySqlHost;

  @Bean
  public JdbcConnectionDetails getMySQLConnection() {

    return new JdbcConnectionDetails() {

      @Override
      public String getUsername() {
        return "root";
      }

      @Override
      public String getPassword() {
        return "password";
      }

      @Override
      public String getJdbcUrl() {
        return "jdbc:mysql://" + mySqlHost + ":3306/app-datastore";
      }
    };
  }
}