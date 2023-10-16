package com.assesment.enums;

import com.assesment.exception.BaseException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
public enum ClientServiceName {
    GITHUB_RETRIEVE_USER("github-retrieve-user");

    private final String value;

    ClientServiceName(final String value ){this.value=value;}

    @JsonValue
    public String toValue(){return getValue();}

    @JsonCreator
    public static ClientServiceName forValue(String code) {
        ClientServiceName result =(ClientServiceName) Stream.of(values()).filter((e)->{
            return e.value.equals(code);
        }).findFirst().orElse(null);
        if (Objects.isNull(result))
        {
            throw new BaseException("Unknown Value : " + code);
        }
        return result;
    }
}
