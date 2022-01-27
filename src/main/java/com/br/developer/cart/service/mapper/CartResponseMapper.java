package com.br.developer.cart.service.mapper;

import com.br.developer.cart.model.response.CartResponse;
import com.br.developer.cart.persistence.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartResponseMapper implements Mapper<Cart, CartResponse> {
    @Override
    public CartResponse map(Cart input) {
        if(input == null) {
            return null;
        }

        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(input.getId());
        cartResponse.setName(input.getName());
        cartResponse.setDescription(input.getDescription());
        cartResponse.setPrice(input.getPrice());


        return cartResponse;
    }
}
