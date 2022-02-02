package com.br.developer.cart.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
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

}
