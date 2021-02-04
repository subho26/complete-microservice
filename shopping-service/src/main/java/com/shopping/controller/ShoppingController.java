package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dto.TransactionRequest;
import com.shopping.model.Order;
import com.shopping.model.Payment;
import com.shopping.service.OrderService;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/placeOrder")
	public Order placeOrder(@RequestBody TransactionRequest request) {

		Order order = request.getOrder();
		order = orderService.placeOrder(order);
		
		Payment payment = orderService.doPayment(order);

		order.setPaymentId(payment.getPaymentId());
		order.setOrderStatus(payment.getPaymentStatus());

		order = orderService.updateOrder(order);

		return order;
	}

}
