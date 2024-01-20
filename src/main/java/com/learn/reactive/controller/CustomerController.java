package com.learn.reactive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.reactive.dto.Customer;
import com.learn.reactive.service.CustomerService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;


    @GetMapping("/rest")
    public List<Customer> getAllCustomers() {
        return service.loadAllCustomers();
    }

    @GetMapping(value = "/reactive",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    @GetMapping(value = "/reactive")
    public Flux<Customer> getAllCustomersStream() {
        return service.loadAllCustomersStream();
    }
}
