package com.br.developer.cart.service;

import com.br.developer.cart.model.request.CartRequest;
import com.br.developer.cart.model.response.CartResponse;
import com.br.developer.cart.persistence.entity.Cart;
import com.br.developer.cart.persistence.repository.CartRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.Assert.notNull;


@Service
public class CartServiceImpl implements CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartResponse create(CartRequest cartRequest) {
        LOGGER.info("Creating a product register");
        notNull(cartRequest, "Invalid Request");
        Cart cart = cartRepository.saveAndFlush(modelMapper.map(cartRequest, Cart.class));
        return modelMapper.map(cart, CartResponse.class);

    }

    @Override
    public Page<CartResponse> getAll(Pageable pageable) {
        LOGGER.info("Searching all registers");
        notNull(pageable, "invalid page");
        return cartRepository.findAll(pageable).map(cart -> modelMapper.map(cart, CartResponse.class));
    }

    @Override
    public Optional<CartResponse> update(Long id, CartRequest cartRequest) {
        LOGGER.info("Updating register");
        notNull(id, "Invalid ID");

        return cartRepository.findById(id)
                .map(cart -> {
                    cart.setName(cartRequest.getName());
                    cart.setDescription(cartRequest.getDescription());
                    cart.setPrice(cartRequest.getPrice());
                    return modelMapper.map(cartRepository.saveAndFlush(cart), CartResponse.class);
                });
    }

    @Override
    public Optional<CartResponse> get(Long id) {
        LOGGER.info("Searching Register");
        notNull(id, "Invalid ID");
        Optional<Cart> byId = cartRepository.findById(id);
        if(byId.isPresent()){
            CartResponse map = modelMapper.map(byId.get(), CartResponse.class);
            return Optional.of(map);
        }
        return Optional.empty();
    }


    @Override
    public List<CartResponse> findBySearch(@RequestParam(value = "q", required = false) String q,
                                           @RequestParam(value = "min_price", required = false) Integer max_price,
                                           @RequestParam(value = "max_price", required = false) Integer min_price) {
        LOGGER.info("Searching Pieces of Information");
//        notNull(q, "Invalid Request");
        return this.cartRepository.findBySearch(q.toUpperCase(), min_price, max_price)
                .stream()
                .map(CartResponse::converter)
                .collect(Collectors.toList());
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
