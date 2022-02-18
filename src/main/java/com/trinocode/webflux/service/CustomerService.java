package com.trinocode.webflux.service;

import com.trinocode.webflux.dao.CustomerDao;
import com.trinocode.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerDao customerDao;

    public List<Customer> getCustomers(){
      long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomers();
        long end =System.currentTimeMillis();
        System.out.println("Time for Execution :"+ (end-start));
        return customers;
    }

    public Flux<Customer> getCustomersReactive(){
        long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomersReactive();
        long end =System.currentTimeMillis();
        System.out.println("Time for Execution :"+ (end-start));
        return customers;
    }
}
