package com.shopping.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderEntity implements Entity {
	private int id;
	private String name;
	private int qty;
	private int paymentId;
	private double price;
	private String orderStatus;

	@Override
	public EntityType getEntityType() {
		return EntityType.OrderEntity;
	}

	@Override
	public String getEntityId() {
		return String.valueOf(this.id);
	}
}
