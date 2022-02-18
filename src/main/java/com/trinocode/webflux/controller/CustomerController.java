package com.trinocode.webflux.controller;

import com.trinocode.webflux.dto.Customer;
import com.trinocode.webflux.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController()
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/normal")
    public List<Customer> getAllCustomers(){
            return  customerService.getCustomers();
    }
    @GetMapping(value = "/reactive",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomersReactive(){
        return  customerService.getCustomersReactive();
    }

}
