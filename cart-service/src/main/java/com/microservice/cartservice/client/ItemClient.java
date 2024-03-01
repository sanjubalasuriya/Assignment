package com.microservice.cartservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ITEM-SERVICE",url = "${application.config.item-url}")
public interface ItemClient {

    @GetMapping("/isExist/{itemId}")
    public boolean isExistItem(@PathVariable("itemId") long itemId);



}
