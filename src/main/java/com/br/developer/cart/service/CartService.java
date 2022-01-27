package com.br.developer.cart.service;

import com.br.developer.cart.model.request.CartRequest;
import com.br.developer.cart.model.response.CartResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface CartService {
    CartResponse create(CartRequest cartRequest);

    Page<CartResponse> getAll(Pageable pageable);

    Optional<CartResponse> update(Long id, CartRequest cartRequest);

    Optional<CartResponse> get(Long id);

    boolean delete(Long id);

}
