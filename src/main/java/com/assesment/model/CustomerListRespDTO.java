package com.assesment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListRespDTO {

    private static final long serialVersionUID = -129778965193395173L;

    private List<CustomerRespDTO> customer;
    private int page;
    private int limit;
}
