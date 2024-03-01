package com.microservice.itemservice.dto.requestDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemRequestDto {

    private String name;
    private double price;
    private int qty;
}
