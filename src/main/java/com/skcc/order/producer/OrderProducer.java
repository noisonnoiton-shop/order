package com.skcc.order.producer;

import com.skcc.order.event.message.OrderEvent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

  private final StreamBridge streamBridge;

  @Value("${domain.name}")
  private String domain;

  public boolean send(OrderEvent orderEvent) {
    log.info("routeTo" + domain + "." + orderEvent.getEventType());

    return this.streamBridge.send("orderOutput", MessageBuilder.withPayload(orderEvent)
    .setHeader("routeTo", domain + "." + orderEvent.getEventType()).build());
  }
}