package com.trinocode.webflux.Handler;

import com.trinocode.webflux.dao.CustomerDao;
import com.trinocode.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request){
        Flux<Customer> customersList = customerDao.getCustomersList();
        return  ServerResponse.ok().body(customersList,Customer.class);
    }

    public Mono<ServerResponse> findCustomers(ServerRequest request){
        int id = Integer.parseInt(request.pathVariable("input"));
        Flux<Customer> customersList = customerDao.getCustomersList();
        //return mono
        //customersList.filter(customer1 -> customer1.getCustomerId() == id).take(1).single();
        Mono<Customer> customer = customersList.filter(customer1 -> customer1.getCustomerId() == id).next();
        return  ServerResponse.ok().body(customer,Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request){
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(customer -> customer.getCustomerName() + ":" + customer.getCustomerId());
        return  ServerResponse.ok().body(saveResponse,String.class);
    }
}
