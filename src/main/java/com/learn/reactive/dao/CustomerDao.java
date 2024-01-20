package com.learn.reactive.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.learn.reactive.dto.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {

	private static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public List<Customer> getRestCustomers() {
		return IntStream.range(1, 10).peek(CustomerDao::sleepExecution)
				.peek(i -> System.out.println("processing count : " + i))
				.mapToObj(i -> Customer.builder().id(i).name("customer"+i).build())
				.collect(Collectors.toList());
	}

	public Flux<Customer> getReactiveCustomers() {
		return Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("processing count in stream flow : " + i))
				.map(i -> Customer.builder().id(i).name("customer "+i).build()).log();
	}

	public Flux<Customer> getReactiveCustomersWithoutDelay() {
		return Flux.range(1, 50)
				.doOnNext(i -> System.out.println("processing count in stream flow : " + i))
				.map(i -> Customer.builder().id(i).name("customer "+i).build())
				.log();
	}
}
