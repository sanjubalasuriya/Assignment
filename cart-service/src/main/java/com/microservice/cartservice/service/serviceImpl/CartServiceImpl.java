package com.microservice.cartservice.service.serviceImpl;

import com.microservice.cartservice.client.ItemClient;
import com.microservice.cartservice.client.UserClient;
import com.microservice.cartservice.dto.request.CartRequestDto;
import com.microservice.cartservice.exception.NotFoundException;
import com.microservice.cartservice.model.Cart;
import com.microservice.cartservice.repository.CartRepository;
import com.microservice.cartservice.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private final ItemClient itemClient;
    private final CartRepository cartRepository;
    private final UserClient userClient;

    public CartServiceImpl(ItemClient itemClient, CartRepository cartRepository, UserClient userClient) {
        this.itemClient = itemClient;
        this.cartRepository = cartRepository;
        this.userClient = userClient;
    }

    @Override
    public String saveCart(CartRequestDto cartRequestDto) {

        if(!itemClient.isExistItem(cartRequestDto.getItemId()) ){
            throw new NotFoundException("Item Not Found");
        } else if (!userClient.isExistUser(cartRequestDto.getUserId())) {
            throw new NotFoundException("User Not Found");
        }else {

            Cart cart = new Cart();
            cart.setQty(cartRequestDto.getQty());
            cart.setProductId(cartRequestDto.getItemId());
            cart.setUserId(cartRequestDto.getUserId());

            cartRepository.save(cart);
            return "saved cart item";
        }


    }

    @Override
    public String delete(long cartId) {
        if(cartRepository.existsById(cartId)){
            Cart cart = cartRepository.getReferenceById(cartId);
            cartRepository.delete(cart);

            return "Deleted "+ cart.getCartId();
        }else {
            throw new NotFoundException("Cart not found");
        }
    }
}
