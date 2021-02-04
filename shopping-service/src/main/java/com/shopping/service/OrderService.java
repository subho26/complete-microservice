package com.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shopping.model.Order;
import com.shopping.model.Payment;
import com.shopping.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestTemplate restTemplate;

	public Order placeOrder(Order order) {
		return orderRepository.save(order);
	}

	public Payment doPayment(Order order) {
		return restTemplate.postForObject("http://PAYMENT-SERVICE/payment/dopayment", order, Payment.class);
	}

	public Order updateOrder(Order order) {
		return orderRepository.save(order);
	}

}
