package com.skcc.order.repository;

import java.util.List;

import com.skcc.order.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>{
  public Order findById(long id);
  public List<Order> findOrderByAccountId(long accountId);
}
