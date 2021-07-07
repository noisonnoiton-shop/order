package com.skcc.order.controller;

import java.util.List;

import com.skcc.order.domain.Order;
import com.skcc.order.event.message.OrderEvent;
import com.skcc.order.service.OrderService;
import com.skcc.payment.event.message.PaymentEvent;
import com.skcc.product.event.message.ProductEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @XRayEnabled
@RequestMapping("/v1")
public class OrderController {
	
	private OrderService orderService;
	
	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping(value="/orders/{accountId}")
	public List<Order> findOrderByAccountId(@PathVariable long accountId) {
		return this.orderService.findOrderByAccountId(accountId); 
	}
	
	@PutMapping(value="/orders")
	public boolean createOrder(@RequestBody Order order) {
		return this.orderService.createOrderAndCreatePublishOrderEvent(order);
	}
	
	@GetMapping(value="/orders/events")
	public List<OrderEvent> getOrderEvent(){
		return this.orderService.getOrderEvent();
	}
	
	//화면에서 삭제시 product 에 연관 txId 넘기기 고려 필요 
	@DeleteMapping(value="/orders/{id}") 
	public boolean cancelOrder(@PathVariable long id) {
		return this.orderService.cancelOrderAndCreatePublishOrderEvent(id);
	}

	@PostMapping(value="/orders/product/cancel")
	public boolean receiveProductAmountSubtractFailedEvent(@RequestBody ProductEvent productEvent) {
		return this.orderService.cancelOrderAndCreatePublishOrderEvent(productEvent);
	}

	@PostMapping(value="/orders/payment/cancel")
	public boolean receivePaymentCreateFailedEvent(@RequestBody PaymentEvent paymentEvent) {
		return this.orderService.cancelOrderAndCreatePublishOrderEvent(paymentEvent);
	}

	@PostMapping(value="/orders/payment/paid")
	public boolean receivePaymentPaidEvent(@RequestBody PaymentEvent paymentEvent) {
		return this.orderService.payOrderAndCreatePublishOrderEvent(paymentEvent);
	}

	@PostMapping(value="/orders/payment")
	public boolean receivePaymentCreatedEvent(@RequestBody PaymentEvent paymentEvent) {
		return this.orderService.setOrderPaymentIdAndCreatePublishOrderEvent(paymentEvent);
	}
	
}
