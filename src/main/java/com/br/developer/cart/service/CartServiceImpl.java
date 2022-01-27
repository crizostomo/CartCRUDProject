package com.br.developer.cart.service;

import com.br.developer.cart.service.mapper.Mapper;
import com.br.developer.cart.model.request.CartRequest;
import com.br.developer.cart.model.response.CartResponse;
import com.br.developer.cart.persistence.entity.Cart;
import com.br.developer.cart.persistence.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.util.Assert.*;


@Service
public class CartServiceImpl implements CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Mapper<CartRequest, Cart> requestMapper;

    @Autowired
    private Mapper<Cart, CartResponse> responseMapper;

    @Override
    public CartResponse create(CartRequest cartRequest) {
        LOGGER.info("Creating a product register");
        notNull(cartRequest, "Invalid Request");
        Cart cart = this.requestMapper.map(cartRequest);
        return cartRepository.saveAndFlush(cart).map((Cart input) -> this.responseMapper.map(input));
    }

    @Override
    public Page<CartResponse> getAll(Pageable pageable) {
        LOGGER.info("Searching all registers");
        notNull(pageable, "invalid page");
        return cartRepository.findAll(pageable).map(cart -> this.responseMapper.map(cart));
    }

    @Override
    public Optional<CartResponse> update(Long id, CartRequest cartRequest) {
        LOGGER.info("Updating register");
        notNull(id, "Invalid ID");

        Cart customerUpdate = this.requestMapper.map(cartRequest);

        return cartRepository.findById(id)
                .map(cart -> {
                    cart.setName(customerUpdate.getName());
                    cart.setDescription(customerUpdate.getDescription());
                    cart.setPrice(customerUpdate.getPrice());
                    return this.responseMapper.map(cartRepository.saveAndFlush(cart));
                });
    }

    @Override
    public Optional<CartResponse> get(Long id) {
        LOGGER.info("Searching Register");
        notNull(id, "Invalid ID");
        return cartRepository.findById(id).map(this.responseMapper::map);
    }

    @Override
    public boolean delete(Long id) {
        LOGGER.info("Removing Register");
        notNull(id, "Invalid ID");

        try{
            cartRepository.deleteById(id);
            return true;
        }catch (Exception e){
            LOGGER.warn("Error when removing register {}", id);
        }

        return false;
    }
}
