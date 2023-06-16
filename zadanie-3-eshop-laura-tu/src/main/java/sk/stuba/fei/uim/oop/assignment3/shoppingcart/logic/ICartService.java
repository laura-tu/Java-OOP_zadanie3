package sk.stuba.fei.uim.oop.assignment3.shoppingcart.logic;

import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import sk.stuba.fei.uim.oop.assignment3.helper.web.WantedProductRequest;
import sk.stuba.fei.uim.oop.assignment3.shoppingcart.data.Cart;


import java.util.List;

public interface ICartService {

    Cart create();

    List<Cart> getAll();

    Cart getById(long id) throws NotFoundException;

    void delete(long id) throws NotFoundException, IllegalOperationException;

    Cart addToCart(long id, WantedProductRequest body) throws NotFoundException, IllegalOperationException;

    double payCart(Long listId) throws NotFoundException, IllegalOperationException;

}
