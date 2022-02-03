package com.br.developer.cart.model.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class CartRequest {

    @NotBlank
    @ApiModelProperty(value = "nome do produto não pode ser vazio")
    private String name;

    @NotBlank
    @ApiModelProperty(value = "descrição do produto não pode ser vazio")
    private String description;

    @NotBlank
    @ApiModelProperty(value = "preço do produto não pode ser vazio")
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
