package com.br.developer.cart.config;

import com.br.developer.cart.model.request.CartRequest;
import com.br.developer.cart.persistence.entity.Cart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ModelMapperConfig {

    @Autowired
    public ModelMapper modelMapper;
}

