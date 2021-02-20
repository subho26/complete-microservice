package com.payment.model;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@Id
	private int id;
	private String name;
	private int qty;
	private int paymentId;
	private double price;
	private String orderStatus;
}
