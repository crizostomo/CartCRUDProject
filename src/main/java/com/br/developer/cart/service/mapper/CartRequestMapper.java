package com.br.developer.cart.service.mapper;

import com.br.developer.cart.model.request.CartRequest;
import com.br.developer.cart.persistence.entity.Cart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartRequestMapper implements Mapper<CartRequest, Cart> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Cart map(CartRequest input) {
        Cart cart = modelMapper.map(input, Cart.class);

        return cart;
    }
}
