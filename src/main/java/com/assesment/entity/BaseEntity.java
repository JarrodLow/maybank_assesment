package com.assesment.entity;

import com.assesment.common.listener.BaseEntityListener;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public class BaseEntity implements Serializable {

    private static final long serialVersionID = 1778847390000617814L;

    @Id
    @Column(
            name = "id"
    )
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Version
    @Column(
            name = "ver"
    )
    private int version;

    @Column(
            name = "created_by",
            updatable = false
    )
    private String createdBy;

    @Column(
            name = "created_dt",
            updatable = false
    )
    private OffsetDateTime createdTime;

    @Column(
            name = "updated_by",
            insertable = false
    )
    private String updatedBy;

    @Column(
            name = "updated_dt",
            insertable = false
    )
    private OffsetDateTime updatedTime;
    @Column(
            name = "sts_cd"
    )
    private String status;
}
