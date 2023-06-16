package sk.stuba.fei.uim.oop.assignment3.helper.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WantedProductRequest {
    private long productId;
    private int amount;
}
