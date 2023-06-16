package sk.stuba.fei.uim.oop.assignment3.shoppingcart.web.bodies;


import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.helper.web.WantedProductResponse;
import sk.stuba.fei.uim.oop.assignment3.shoppingcart.data.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class CartResponse {
    private long id;
    private List<WantedProductResponse> shoppingList;
    private boolean payed;

    public CartResponse(Cart shoppingCart) {
        this.id = shoppingCart.getId();
        this.payed = shoppingCart.isPayed();
        this.shoppingList = shoppingCart.getShoppingList().stream().map(WantedProductResponse::new).collect(Collectors.toList());
    }
}
