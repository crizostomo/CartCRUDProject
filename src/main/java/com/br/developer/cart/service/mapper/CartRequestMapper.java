package com.br.developer.cart.service.mapper;

import com.br.developer.cart.model.request.CartRequest;
import com.br.developer.cart.persistence.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartRequestMapper implements Mapper<CartRequest, Cart> {

    @Override
    public Cart map(CartRequest input) {
        if(input == null) {
            return null;
        }
        Cart cart = new Cart();
        cart.setName(input.getName());
        cart.setDescription(input.getDescription());
        cart.setPrice(input.getPrice());


        return cart;
    }
}
