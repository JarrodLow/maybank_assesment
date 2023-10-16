package com.assesment.service;

import com.assesment.data.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import com.assesment.model.CustomerRespDTO;
import com.assesment.data.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CustomerCommandService {

    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CustomerRespDTO saveCustomer(Customer customer)
    {
        Customer customerResp = customerRepository.saveAndFlush(customer);
        return CustomerRespDTO.fromEntityToDTO(customerResp,objectMapper);
    }


}
