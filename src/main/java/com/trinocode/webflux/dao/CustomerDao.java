package com.trinocode.webflux.dao;

import com.trinocode.webflux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public static void pauseExecution(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 10)
                .peek(CustomerDao::pauseExecution)
                .peek(i -> System.out.println("processing count :" + i))
                .mapToObj(i -> new Customer(i, "customer" + i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersReactive() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Processing in stream :" + i))
                .map(i -> new Customer(i, "Customer" + i));
    }


    public Flux<Customer> getCustomersList() {
        return Flux.range(1, 10)
                .doOnNext(i -> System.out.println("Processing in stream :" + i))
                .map(i -> new Customer(i, "Customer" + i));
    }
}