package com.microservice.itemservice.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemResponseDto {

    private long itemId;
    private String name;
    private double price;
    private int qty;
}
