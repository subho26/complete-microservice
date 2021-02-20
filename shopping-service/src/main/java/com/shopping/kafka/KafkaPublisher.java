package com.shopping.kafka;

import com.shopping.model.OrderEntity;

public interface KafkaPublisher {
	void publishOrder(OrderEntity orderEntity);
}
