package com.assesment.service;

import com.assesment.common.host.GithubClientService;
import com.assesment.enums.ActionType;
import com.assesment.data.Customer;
import com.assesment.enums.StatusCode;
import com.assesment.exception.BaseException;
import com.assesment.model.CustomerDetail;
import com.assesment.model.CustomerListRespDTO;
import com.assesment.model.CustomerReq;
import com.assesment.model.CustomerRespDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService {

    private final ObjectMapper objectMapper;
    private final CustomerCommandService customerCommandService;
    private final CustomerQueryService customerQueryService;
    private final CustomerValidateService customerValidateService;
    private final GithubClientService githubClientService;
    public CustomerRespDTO processCustomer(CustomerReq customerReq, ActionType actionType) {
        customerValidateService.validateCustomer(customerReq, actionType);
        if (Objects.isNull(customerReq.getId()) && ActionType.CREATE.equals(actionType)) {
            return createCustomer(customerReq);
        } else {
            Customer existCustomer = customerQueryService.findByCustomerId(customerReq.getId());
            customerValidateService.validateVersion(customerReq, actionType, existCustomer);
            if(ActionType.UPDATE.equals(actionType))
            {
                return updateCustomer(customerReq,existCustomer);
            }
            else if(ActionType.DELETE.equals(actionType))
            {
                return deleteCustomer(existCustomer);
            }
            throw new BaseException("System Failure. ");
        }
    }

    @SneakyThrows
    private CustomerRespDTO createCustomer(CustomerReq customerReq) {
        Customer customer = objectMapper.convertValue(customerReq, Customer.class);
        CustomerDetail customerDetail = objectMapper.convertValue(customerReq.getCustomerDetail(), CustomerDetail.class);
        checkGithubAccess(customerDetail);

        customer.setDetail(objectMapper.writeValueAsString(customerDetail));
        return customerCommandService.saveCustomer(customer);
    }

    @SneakyThrows
    private CustomerRespDTO updateCustomer(CustomerReq customerReq,Customer existCustomer)
    {
        CustomerDetail customerDetail = objectMapper.convertValue(customerReq.getCustomerDetail(), CustomerDetail.class);
        checkGithubAccess(customerDetail);
        existCustomer.setDetail(objectMapper.writeValueAsString(customerDetail));
        existCustomer.setName(customerReq.getName());
        return customerCommandService.saveCustomer(existCustomer);
    }
    private CustomerRespDTO deleteCustomer(Customer existCustomer)
    {
        existCustomer.setStatus(StatusCode.DELETED.toValue());
        return customerCommandService.saveCustomer(existCustomer);
    }

    public CustomerRespDTO retrieveCustomerDetail(UUID customerId)
    {
        Customer customer = customerQueryService.findByCustomerId(customerId);
        return CustomerRespDTO.fromEntityToDTO(customer,objectMapper);
    }

    public CustomerListRespDTO customerInquiryList(String name,Pageable pageable)
    {
        Page<Customer> customer = customerQueryService.retrieveAllCustomerWithPageable(name,pageable);
        List<CustomerRespDTO> customerRespDTOS = new ArrayList<>();
        customer.getContent().forEach(x->{
            customerRespDTOS.add(CustomerRespDTO.fromEntityToDTO(x,objectMapper));
        });
        CustomerListRespDTO customerListRespDTO = new CustomerListRespDTO();
        customerListRespDTO.setCustomer(customerRespDTOS);
        customerListRespDTO.setPage(pageable.getPageNumber()+1);
        customerListRespDTO.setLimit(pageable.getPageSize());
        return customerListRespDTO;
    }

    private CustomerDetail checkGithubAccess(CustomerDetail customerDetail)
    {
        if(!ObjectUtils.isEmpty(customerDetail.getGithubUserName()))
        {
            customerDetail.setHaveGithub(githubClientService.retrieveUser(customerDetail.getGithubUserName()));
            return customerDetail;
        }
        customerDetail.setHaveGithub(false);
        return customerDetail;
    }


}
