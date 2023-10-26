package com.assesment.common.host;

import com.assesment.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GithubClientService {
    @Bean
    private WebClient webClient() {
        return WebClient.create("https://api.github.com"); // Replace with your base URL
    }

    public Boolean retrieveUser(String userName) {
        Map<String, Serializable> result=this.webClient()
                .method(HttpMethod.GET)
                .uri("/users/" + userName)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Serializable>>() {})//to decode the body to given type
                .doOnError(throwable -> {
                    throw new BaseException("Error Client Service");
                })
                .block(); //to sleep the webclient aftter done
        return !Objects.isNull(result) && !Objects.isNull(result.get("login"));
    }

}
