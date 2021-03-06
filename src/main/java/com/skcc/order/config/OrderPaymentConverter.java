package com.skcc.order.config;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skcc.order.domain.OrderPayment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderPaymentConverter implements AttributeConverter<OrderPayment, String> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(OrderPayment tData) {

    try {
      return objectMapper.writeValueAsString(tData);
    } catch (JsonProcessingException e) {
      log.error("fail to serialize as object into Json : {}", tData, e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public OrderPayment convertToEntityAttribute(String jsonStr) {

    try {
      return objectMapper.readValue(jsonStr, OrderPayment.class);
    } catch (IOException e) {
      log.error("fail to deserialize as Json into Object : {}", jsonStr, e);
      throw new RuntimeException(e);
    }
  }
}
