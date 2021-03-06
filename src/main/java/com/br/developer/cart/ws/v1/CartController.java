package com.br.developer.cart.ws.v1;

import com.br.developer.cart.model.request.CartRequest;
import com.br.developer.cart.model.response.CartResponse;
import com.br.developer.cart.persistence.repository.CartRepository;
import com.br.developer.cart.service.CartService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class CartController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    private final CartRepository cartRepository;


    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    private CartService cartService;

    @ApiOperation("API responsável por criar os produtos")
    @ApiResponses(value = {
           @ApiResponse(code = 200, message = "Cria um produto"),
           @ApiResponse(code = 500, message = "Foi gerado um erro ao criar um produto")
    })
    @PostMapping
    public ResponseEntity<CartResponse> create(@RequestBody CartRequest cartRequest){
        LOGGER.info("Requesting received");
        return ResponseEntity.ok(cartService.create(cartRequest));
    }

    @ApiOperation("API responsável por listar os produtos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Criação do produto ok"),
            @ApiResponse(code = 500, message = "Foi gerado um erro ao criar um produto")
    })
    @GetMapping
    public ResponseEntity<Page<CartResponse>> getAll(Pageable pageable){
        LOGGER.info("Searching Registers");
        Page<CartResponse> customerResponses = cartService.getAll(pageable);
        return ResponseEntity.ok(customerResponses);
    }

    @ApiOperation("API responsável por atualizar os produtos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Atualização do produto ok"),
            @ApiResponse(code = 500, message = "Foi gerado um erro ao atualizar um produto")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CartResponse> update(@PathVariable("id") Long id, @RequestBody CartRequest cartRequest){
        LOGGER.info("Initializing update");
        Optional<CartResponse> update = cartService.update(id, cartRequest);
        if(!update.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(update.get());
    }

    @ApiOperation("API responsável por buscar um único produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca do produto ok"),
            @ApiResponse(code = 500, message = "Foi gerado um erro ao buscar um produto")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> get(@PathVariable("id") Long id){
        LOGGER.info("Starting searches for registers");
        Optional<CartResponse> cartResponse = cartService.get(id);
        if(!cartResponse.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cartResponse.get());
    }

    @ApiOperation("API responsável por buscar os produtos de forma customizada")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Procura do produto de forma customizada ok"),
            @ApiResponse(code = 500, message = "Foi gerado um erro ao buscar um produto de forma customizada")
    })
    @GetMapping("/search")
    public ResponseEntity<List<CartResponse>> findBySearch(@RequestParam(value = "q", required = false) String q,
                                           @RequestParam(value = "min_price", required = false) Integer max_price,
                                           @RequestParam(value = "max_price", required = false) Integer min_price) {
        LOGGER.info("Searching part of the query");
        List<CartResponse> cartResponses = cartService.findBySearch(q.toUpperCase(), min_price, max_price);
        return ResponseEntity.ok(cartResponses);
    }

    @ApiOperation("API responsável por remover um único produto por vez")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Delete do produto ok"),
            @ApiResponse(code = 500, message = "Foi gerado um erro ao deletar o produto")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        LOGGER.info("Starting register removing");
        if(cartService.delete(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}

//@Positive @Valid @RestControllerAdvice @Lombok @Mapper
