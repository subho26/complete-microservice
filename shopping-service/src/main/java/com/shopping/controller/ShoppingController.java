package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dto.TransactionRequest;
import com.shopping.kafka.KafkaPublisher;
import com.shopping.model.Order;
import com.shopping.model.OrderEntity;
import com.shopping.model.Payment;
import com.shopping.service.OrderService;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {
	@Autowired
	private OrderService orderService;

	@Autowired
	KafkaPublisher kafkaPublisher;

	@PostMapping("/placeorder")
	public Order placeOrder(@RequestBody TransactionRequest request) {

		Order order = request.getOrder();
		order = orderService.placeOrder(order);

		Payment payment = orderService.doPayment(order);

		order.setPaymentId(payment.getPaymentId());
		order.setOrderStatus(payment.getPaymentStatus());

		order = orderService.updateOrder(order);

		final OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(order.getId());
		orderEntity.setName(order.getName());
		orderEntity.setOrderStatus(order.getOrderStatus());
		orderEntity.setPaymentId(payment.getPaymentId());
		orderEntity.setPrice(payment.getOrderAmount());
		orderEntity.setQty(order.getQty());

		kafkaPublisher.publishOrder(orderEntity);

		return order;
	}

	@GetMapping("/orders")
	public List<Order> placeOrder() {
		return orderService.getAllOrders();
	}

}
