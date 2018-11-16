package com.yrk.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class UserController {

	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("findOrdersByUser/{id}")
	@HystrixCommand(fallbackMethod = "fallbackInfo")
	public String findOrdersByUser(@PathVariable String id) {
		int oid = 23;
		return this.restTemplate.getForObject("http://microservice-eureka-order/order/" + oid, String.class);
	}
	
	
	public String fallbackInfo(@PathVariable String id) {
		return "Service is not available";
	}
}
