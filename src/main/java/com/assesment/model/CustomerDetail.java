package com.assesment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDetail implements Serializable {


    @NotNull
    @JsonProperty("customerIdNo")
    private String customerIdNo;

    @JsonProperty("haveGithub")
    private boolean haveGithub;

    @NotNull
    @JsonProperty("githubUserName")
    private String githubUserName;
}
