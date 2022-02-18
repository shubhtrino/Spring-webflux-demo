package com.trinocode.webflux.dao;

import com.trinocode.webflux.dto.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    public static void pauseExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public List<Customer> getCustomers(){
        return IntStream.rangeClosed(1,50)
                .peek(CustomerDao::pauseExecution)
                .peek(i -> System.out.println("processing count :"+i))
                .mapToObj(i -> new Customer(i,"customer"+1))
                .collect(Collectors.toList());
    }


}
