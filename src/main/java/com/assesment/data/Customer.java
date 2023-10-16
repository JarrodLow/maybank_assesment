package com.assesment.data;

import com.assesment.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Getter
@Setter
@Entity(name = "t_customer")
@Where(clause = "sts_cd not in ('D','I')")
public class Customer extends BaseEntity {

    @Column(
            name = "name"
    )
    private String name;

    @Column(
            name = "detail"
    )
    private String detail;
}
