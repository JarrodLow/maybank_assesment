package com.assesment.common.listener;

import com.assesment.entity.BaseEntity;
import com.assesment.enums.StatusCode;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.OffsetDateTime;

@Component
public class BaseEntityListener {
    public BaseEntityListener(){}

    @PrePersist
    public void onPrePersist(BaseEntity baseEntity) {
        baseEntity.setCreatedBy("sys");
        baseEntity.setCreatedTime(OffsetDateTime.now());
        baseEntity.setVersion(0);
        baseEntity.setStatus(StatusCode.ACTIVE.toValue());
    }

    @PreUpdate
    public void onPreUpdate(BaseEntity baseEntity) {
        baseEntity.setUpdatedBy("sys");
        baseEntity.setUpdatedTime(OffsetDateTime.now());
    }
}
