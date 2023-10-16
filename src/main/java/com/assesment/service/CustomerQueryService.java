package com.assesment.service;

import com.assesment.data.Customer;
import com.assesment.exception.BaseException;
import com.assesment.data.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerQueryService {
    private final CustomerRepository customerRepository;

    public Customer findByCustomerId(UUID id)
    {
        Optional<Customer> result = customerRepository.findById(id);
        if(result.isPresent())
        {
            return result.get();

        }
        throw new BaseException("Record not found");
    }

    public Page<Customer> retrieveAllCustomerWithPageable(String name,Pageable pageable)
    {
        return customerRepository.findAllByNameContains(name,pageable);
    }
}
