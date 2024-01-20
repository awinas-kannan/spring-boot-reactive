package com.learn.reactive.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.learn.reactive.dao.CustomerDao;
import com.learn.reactive.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Autowired
    private CustomerDao dao;


    public Mono<ServerResponse> getCustomersStream(ServerRequest request) {
        Flux<Customer> customersStream = dao.getReactiveCustomers();
        return ServerResponse.ok().
                contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customersStream, Customer.class);
    }
    
    public Mono<ServerResponse> getReactiveCustomersWithoutDelay(ServerRequest request) {
        Flux<Customer> customersStream = dao.getReactiveCustomersWithoutDelay();
        return ServerResponse.ok().
                contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customersStream, Customer.class);
    }
}
