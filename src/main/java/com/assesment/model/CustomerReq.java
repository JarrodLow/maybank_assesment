package com.assesment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.assesment.interfaces.CustomerUpdateValidate;
import com.assesment.interfaces.CustomerVersionValidate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerReq {
    @NotNull(groups = CustomerUpdateValidate.class)
    private UUID id;
    private String name;
    @NotNull
    @Valid
    private CustomerDetail customerDetail;
    @NotNull(groups = CustomerVersionValidate.class)
    private Integer version;
}
