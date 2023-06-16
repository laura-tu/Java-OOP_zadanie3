package sk.stuba.fei.uim.oop.assignment3.helper.web;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.helper.data.WantedProduct;

@Getter
public class WantedProductResponse {
    private Long productId;
    private int amount;


    public WantedProductResponse(WantedProduct h) {
        this.productId = h.getProductId();
        this.amount = h.getAmount();
    }
}
