package com.br.developer.cart.persistence.repository;

import com.br.developer.cart.persistence.entity.Cart;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CartCustomRepository {

    private final EntityManager em;

    public CartCustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Cart> find(Long id, String name, String description, Integer price) {

        String query = "select C from Cart as C ";
        String condition = "where";

        if(id != null) {
            query += condition + " C.id = :id";
            condition = " and ";
        }

        if(name != null) {
            query += condition + " C.name = :name";
            condition = " and ";
        }

        if(description != null) {
            query += condition + " C.description = :description";
            condition = " and ";
        }

        if(price != null) {
            query += condition + " C.price = :price";
        }


        var c = em.createQuery(query, Cart.class);

        if(id != null) {
            c.setParameter("id", id);
        }

        if(name != null) {
            c.setParameter("name", name);
        }

        if(description != null) {
            c.setParameter("description", description);
        }

        if(price != null) {
            c.setParameter("price", price);
        }

        return c.getResultList();
    }

}
