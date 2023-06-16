package sk.stuba.fei.uim.oop.assignment3.shoppingcart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import sk.stuba.fei.uim.oop.assignment3.helper.web.WantedProductRequest;

import sk.stuba.fei.uim.oop.assignment3.shoppingcart.logic.ICartService;
import sk.stuba.fei.uim.oop.assignment3.shoppingcart.web.bodies.CartResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartResponse> addList() {
        return new ResponseEntity<>(new CartResponse(this.service.create()), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CartResponse> getAllLists() {
        return this.service.getAll().stream().map(CartResponse::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public CartResponse getList(@PathVariable("id") long listId) throws NotFoundException {
        return new CartResponse(this.service.getById(listId));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") long listId) throws NotFoundException, IllegalOperationException {
        this.service.delete(listId);
    }

    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse addToCart(@PathVariable("id") Long listId, @RequestBody WantedProductRequest body) throws NotFoundException, IllegalOperationException {
        return new CartResponse(this.service.addToCart(listId, body));
    }


    @GetMapping(value = "/{id}/pay", produces = MediaType.TEXT_PLAIN_VALUE)
    public String payList(@PathVariable("id") Long listId) throws NotFoundException, IllegalOperationException {
        double d = (this.service.payCart(listId));
        return String.valueOf(d);
    }
}
