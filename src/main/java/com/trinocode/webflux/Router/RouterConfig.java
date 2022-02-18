package com.trinocode.webflux.Router;

import com.trinocode.webflux.Handler.CustomerHandler;
import com.trinocode.webflux.Handler.CustomerReactiveHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    CustomerHandler  customerHandler;

    @Autowired
    CustomerReactiveHandler customerReactiveHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/router/customers",customerHandler::loadCustomers)
                .GET("/router/customers/reactive",customerReactiveHandler::loadCustomers)
                 .GET("/router/customers/{input}",customerHandler::findCustomers)
                .POST("/router/customers/save",customerHandler::saveCustomer)

                .build();
    }
}
