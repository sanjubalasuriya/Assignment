package com.microservice.cartservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AUTH-SERVICE",url = "${application.config.auth-url}")
public interface UserClient {

    @GetMapping("/isExist/{userId}")
    public boolean isExistUser(@PathVariable("userId") long userId);
}
