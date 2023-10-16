package com.assesment.model;

import com.assesment.data.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import lombok.*;
import org.springframework.core.ParameterizedTypeReference;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRespDTO  implements Serializable {

    private UUID id;
    private String customerName;
    private Integer version;
    private String statusCode;
    private String createdBy;
    private String updatedBy;
    private CustomerDetail customerDetail;
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    private OffsetDateTime createdTime;
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    private OffsetDateTime updatedTime;

    @SneakyThrows
    public static CustomerRespDTO fromEntityToDTO(Customer customer, ObjectMapper objectMapper)
    {
        CustomerRespDTO customerRespDTO = new CustomerRespDTO();
        customerRespDTO.setId(customer.getId());
        customerRespDTO.setCustomerName(customer.getName());
        HashMap<String, Serializable> detail = ( objectMapper.readValue(customer.getDetail(), HashMap.class));
        customerRespDTO.setCustomerDetail(objectMapper.convertValue(detail,CustomerDetail.class));
        customerRespDTO.setCreatedBy(customer.getCreatedBy());
        customerRespDTO.setCreatedTime(customer.getCreatedTime());
        customerRespDTO.setUpdatedBy(customer.getUpdatedBy());
        customerRespDTO.setUpdatedTime(customer.getUpdatedTime());
        customerRespDTO.setStatusCode(customer.getStatus());
        customerRespDTO.setVersion(customer.getVersion());
        return customerRespDTO;
    }

}
