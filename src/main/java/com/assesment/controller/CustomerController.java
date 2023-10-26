package com.assesment.controller;

import com.assesment.common.host.GithubClientService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.assesment.common.model.RestResponse;
import com.assesment.enums.ActionType;
import com.assesment.exception.BaseException;
import com.assesment.model.CustomerListRespDTO;
import com.assesment.model.CustomerReq;
import com.assesment.model.CustomerRespDTO;
import com.assesment.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/customer")
@RequiredArgsConstructor
public class CustomerController{

    private final CustomerService customerService;

    @PostMapping(value = "/action/{actionType}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "To process Customer")
    public RestResponse<CustomerRespDTO> process(
            @PathVariable("actionType")ActionType actionType,
            @RequestBody CustomerReq customerReq)
    {
        try{
            CustomerRespDTO result = customerService.processCustomer(customerReq,actionType);
            return RestResponse.success(result);
        }catch (BaseException ex)
        {
            return RestResponse.failure(ex.getMsg());
        }
    }

    @GetMapping(value = "/detail/{customerId}")
    @Operation(summary = "Get Customer Detail")
    public RestResponse<CustomerRespDTO> getCustomerDetail(
            @PathVariable("customerId") UUID customerId
    ){
        try{
            CustomerRespDTO result = customerService.retrieveCustomerDetail(customerId);
            return RestResponse.success(result);
        }catch (BaseException ex)
        {
            return RestResponse.failure(ex.getMsg());
        }
    }

    @GetMapping(value = "/inquiry")
    @Operation(summary = "Get Customer Detail")
    public RestResponse<CustomerListRespDTO> retrievCustomerList(
            @RequestParam String name,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int limit
    ){

        Pageable pageable = PageRequest.of(page -1 , limit);
        try{
            CustomerListRespDTO result = customerService.customerInquiryList(name,pageable);
            return RestResponse.success(result);
        }catch (BaseException ex)
        {
            return RestResponse.failure(ex.getMsg());
        }
    }


}
