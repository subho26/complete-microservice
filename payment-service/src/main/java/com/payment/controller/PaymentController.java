package com.payment.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.model.Order;
import com.payment.model.Payment;
import com.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/dopayment")
	public Payment doPayment(@RequestBody Order order) {
		Payment payment = new Payment();
		try {
			payment.setOrderId(order.getId());
			payment.setOrderAmount(order.getPrice() * order.getQty());
			payment.setTransactionId(UUID.randomUUID().toString());
			payment.setPaymentStatus("SUCCESS");
			payment = paymentService.doPayment(payment);
			return payment;
		} catch (Exception e) {
			payment.setPaymentStatus("FAIL");
			return payment;
		}
	}

}
