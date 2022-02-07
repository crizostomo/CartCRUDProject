package com.br.developer.cart.model.response;

import com.br.developer.cart.persistence.entity.Cart;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartResponse {

    private Long id;
    private String name;
    private String description;
    private Integer price;

    public static CartResponse converter(Cart c) {
        var cart = new CartResponse();
        cart.setId(c.getId());
        cart.setName(c.getName());
        cart.setDescription(c.getDescription());
        cart.setPrice(c.getPrice());
        return cart;
    }


}
