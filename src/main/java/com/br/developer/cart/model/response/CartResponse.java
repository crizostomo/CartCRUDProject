package com.br.developer.cart.model.response;

import com.br.developer.cart.persistence.entity.Cart;

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
