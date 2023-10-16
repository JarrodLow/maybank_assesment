package com.assesment.service;

import com.assesment.common.utils.ValidateUtils;
import com.assesment.enums.ActionType;
import com.assesment.data.Customer;
import com.assesment.exception.BaseException;
import com.assesment.interfaces.CustomerUpdateValidate;
import com.assesment.interfaces.CustomerVersionValidate;
import com.assesment.model.CustomerReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerValidateService {

    private static final List<ActionType> modifyRelatedActionType = Arrays.asList(ActionType.UPDATE,ActionType.DELETE);

    public void validateCustomer(CustomerReq customerReq, ActionType actionType)
    {
        ValidateUtils.validate(customerReq);
        if(ActionType.UPDATE.equals(actionType))
        {
            ValidateUtils.validate(customerReq, CustomerUpdateValidate.class);
        }
    }

    public void validateVersion(CustomerReq customerReq, ActionType actionType , Customer existingCustomer) {
        ValidateUtils.validate(customerReq, CustomerVersionValidate.class);
        if (modifyRelatedActionType.contains(actionType) && customerReq.getVersion() != existingCustomer.getVersion())
        {
            throw new BaseException("Records has been updated by another User");
        }
    }

}
