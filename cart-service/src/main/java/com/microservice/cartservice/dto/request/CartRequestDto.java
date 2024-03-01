package com.microservice.cartservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartRequestDto {

    private int qty;
    private long itemId;
    private long userId;
}
