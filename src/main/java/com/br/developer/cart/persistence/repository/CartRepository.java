package com.br.developer.cart.persistence.repository;

import com.br.developer.cart.persistence.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Repository
@RestControllerAdvice
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByNameContains(String name);

    @Query("select u from Cart u where (:q is null or UPPER(u.name) like %:q% or UPPER(u.description) like %:q%) and (:min_price is null or u.price >= :min_price) and (:max_price is null or u.price <= :max_price)")
    List<Cart> findBySearch(@Param("q") String q, @Param("min_price") Integer min_price, @Param("max_price") Integer max_price);

//    @Query("SELECT u FROM Cart u WHERE u.name = ?1 or u.description = ?2 or u.price = ?3")
//    List<Cart> findProductByNameOrDescriptionOrPrice(String name, String description, Integer price);

}
