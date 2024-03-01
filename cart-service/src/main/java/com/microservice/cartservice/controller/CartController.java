package com.microservice.cartservice.controller;

import com.microservice.cartservice.client.ItemClient;
import com.microservice.cartservice.dto.request.CartRequestDto;
import com.microservice.cartservice.service.CartService;
import com.microservice.cartservice.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;

    }

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveCart(@RequestBody CartRequestDto cartRequestDto) {

        String message = cartService.saveCart(cartRequestDto);
        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "success", message)
                , HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StandardResponse> deleteCart(@PathVariable(value = "id") long cartId) {
        String message = cartService.delete(cartId);
        return new ResponseEntity<StandardResponse>(new StandardResponse(200, "success", message)
                , HttpStatus.OK);
    }
}
