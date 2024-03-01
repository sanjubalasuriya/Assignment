package com.microservice.cartservice.service;


import com.microservice.cartservice.dto.request.CartRequestDto;

public interface CartService {
    String saveCart(CartRequestDto cartRequestDto);

    String delete(long cartId);
}
