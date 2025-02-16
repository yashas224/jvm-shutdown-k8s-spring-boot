package com.example.poc.data_jpa_poc.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("!local")
public class MyReadinessStateExporter {

  @Value("${MY_NODE_NAME}")
  private String nodeName;

  @Value("${MY_POD_NAME}")
  private String podName;

  @Value("${MY_POD_NAMESPACE}")
  private String namespace;

  @Value("${MY_POD_IP}")
  private String podIp;

  @EventListener
  public void onStateChange(AvailabilityChangeEvent<ReadinessState> event) {
    switch(event.getState()) {
      case ACCEPTING_TRAFFIC -> log.info("Started pod: {} ", podName);
    }
  }
}