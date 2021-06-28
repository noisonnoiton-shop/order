package com.skcc.payment.event.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentEvent {
	
	private long id;
	private String domain;
	private long paymentId;
	private PaymentEventType eventType;
	private PaymentPayload payload;
	private String txId;
	private String createdAt;
}
