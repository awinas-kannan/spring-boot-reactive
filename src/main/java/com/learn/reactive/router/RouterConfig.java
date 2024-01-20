package com.learn.reactive.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.learn.reactive.handler.CustomerHandler;
import com.learn.reactive.handler.CustomerStreamHandler;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomerStreamHandler streamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/router/customer",handler::getCustomers)
                .GET("/router/customer/{input}",handler::findCustomer)
                .POST("/router/customer/save",handler::saveCustomer)
                
                .GET("/router/customers/stream",streamHandler::getCustomersStream)
                .GET("/router/customers/nd/stream",streamHandler::getReactiveCustomersWithoutDelay)
                .build();

    }
}
