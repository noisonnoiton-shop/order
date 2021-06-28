package com.skcc.order.config;

import java.io.IOException;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skcc.order.domain.OrderProduct;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderProductConverter implements AttributeConverter<List<OrderProduct>, String> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(List<OrderProduct> tData) {

    try {
      return objectMapper.writeValueAsString(tData);
    } catch (JsonProcessingException e) {
      log.error("fail to serialize as object into Json : {}", tData, e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<OrderProduct> convertToEntityAttribute(String jsonStr) {

    try {
      return objectMapper.readValue(jsonStr, new TypeReference<List<OrderProduct>>(){});
    } catch (IOException e) {
      log.error("fail to deserialize as Json into Object : {}", jsonStr, e);
      throw new RuntimeException(e);
    }
  }
}
