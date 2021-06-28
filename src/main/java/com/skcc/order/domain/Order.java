package com.skcc.order.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.skcc.order.config.OrderAccountConverter;
import com.skcc.order.config.OrderPaymentConverter;
import com.skcc.order.config.OrderProductConverter;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Orders")
@SequenceGenerator(name = "order_seq_gen", sequenceName = "order_seq", allocationSize = 1, initialValue = 1)
public class Order {
	
	@GeneratedValue(strategy=GenerationType.TABLE, generator="order_seq_gen")

	@Id
	private Long id;

	@Column
	private Long accountId;

	@Column
	private Long paymentId;

	@Column(length = 255)
	private String productActive;

	@Column
	private Long productQuantity;
	
	@Column(columnDefinition = "TEXT")
	@Convert(converter = OrderAccountConverter.class)
	private OrderAccount accountInfo;

	@Column(columnDefinition = "TEXT")
	@Convert(converter = OrderProductConverter.class)
	private List<OrderProduct> productsInfo;

	@Column(columnDefinition = "TEXT")
	@Convert(converter = OrderPaymentConverter.class)
	private OrderPayment paymentInfo;

	@Column(length = 255)
	private String paid;

	@Column(length = 255)
	private String status;

	@Column
	@CreationTimestamp
	private LocalDateTime createdAt;
}
