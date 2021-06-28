package com.skcc.order.repository;

import com.skcc.order.event.message.OrderEvent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEventRepository extends JpaRepository<OrderEvent, Long>{
  public OrderEvent findOrderEventByTxIdAndEventType(String txId, String eventType);
}
