package com.br.developer.cart.ws.v1;

import com.br.developer.cart.model.request.CartRequest;
import com.br.developer.cart.model.response.CartResponse;
import com.br.developer.cart.persistence.entity.Cart;
import com.br.developer.cart.persistence.repository.CartCustomRepository;
import com.br.developer.cart.persistence.repository.CartRepository;
import com.br.developer.cart.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1")
public class CartController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    private final CartRepository cartRepository;
    private final CartCustomRepository cartCustomRepository;


    public CartController(CartRepository cartRepository, CartCustomRepository cartCustomRepository) {
        this.cartRepository = cartRepository;
        this.cartCustomRepository = cartCustomRepository;
    }

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponse> create(@RequestBody CartRequest cartRequest){
        LOGGER.info("Requesting received");
        return ResponseEntity.ok(cartService.create(cartRequest));
    }

    @GetMapping
    public ResponseEntity<Page<CartResponse>> getAll(Pageable pageable){
        LOGGER.info("Searching Registers");
        Page<CartResponse> customerResponses = cartService.getAll(pageable);
        return ResponseEntity.ok(customerResponses);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CartResponse> update(@PathVariable("id") Long id, @RequestBody CartRequest cartRequest){
        LOGGER.info("Initializing update");
        Optional<CartResponse> update = cartService.update(id, cartRequest);
        if(!update.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(update.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> get(@PathVariable("id") Long id){
        LOGGER.info("Starting searches for registers");
        Optional<CartResponse> cartResponse = cartService.get(id);
        if(!cartResponse.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartResponse.get());
    }

    @GetMapping("/filter")
    public List<CartResponse> findProductByName(@RequestParam("name") String name) {
        LOGGER.info("Searching name");
        return this.cartRepository.findByNameContains(name)
                .stream()
                .map(CartResponse::converter)
                .collect(Collectors.toList());
    }

    @GetMapping("/filter/2")
    public List<CartResponse> findBySearch(@RequestParam("q") String q, @RequestParam("min_price") Integer max_price, @RequestParam("max_price") Integer min_price) {
        LOGGER.info("Searching part of the query");
        return this.cartRepository.findBySearch(q, min_price, max_price)
                .stream()
                .map(CartResponse::converter)
                .collect(Collectors.toList());
    }


    @GetMapping("/filter/custom")
    public List<CartResponse> findProductByCustom(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "price", required = false) Integer price
    ) {
        LOGGER.info("Searching a specific query");
        return this.cartCustomRepository.find(id, name, description, price)
                .stream()
                .map(CartResponse::converter)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        LOGGER.info("Starting register removing");
        if(cartService.delete(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
