package com.learn.reactive.monoflux;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

	@Test
	public void testMono() {
		Mono<String> mono = Mono.just("Awinas").log();
		mono.subscribe(System.out::println);

		mono.subscribe(e -> {
			System.out.println(e);
		});

		Consumer<String> cons = new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		};

		mono.subscribe(cons);

		Mono<?> monoErr = Mono.just("Awinas").then(Mono.error(new Exception("Exception In Mono"))).log();
		monoErr.subscribe(System.out::println, (e) -> {
			System.out.println(e.getMessage());
		});
	}

	@Test
	public void testFlux() {
		Flux<?> flux = Flux.just("val 1", "val 2", "val 3", "val 4")
				.concatWithValues("concat 1", "concat 2", "concat 3", "concat 4")
				.concatWith(Flux.error(new Exception("Exception In Flux")))
				.concatWithValues("Check if onNext Printed after Error")
				.log();
		flux.subscribe(e -> System.out.println("Printing " + e), e -> {
			System.out.println("Printing " +e.getMessage());
		});
	}
}
