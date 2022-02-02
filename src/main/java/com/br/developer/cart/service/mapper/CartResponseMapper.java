package com.br.developer.cart.service.mapper;

import com.br.developer.cart.model.response.CartResponse;
import com.br.developer.cart.persistence.entity.Cart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartResponseMapper implements Mapper<Cart, CartResponse> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartResponse map(Cart input) {
        CartResponse cartResponse = modelMapper.map(input, CartResponse.class);

        return cartResponse;
    }
}
