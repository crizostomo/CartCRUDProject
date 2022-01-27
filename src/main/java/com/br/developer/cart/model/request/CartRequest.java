package com.br.developer.cart.model.request;

import javax.validation.constraints.NotBlank;

public class CartRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private Integer price;

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
