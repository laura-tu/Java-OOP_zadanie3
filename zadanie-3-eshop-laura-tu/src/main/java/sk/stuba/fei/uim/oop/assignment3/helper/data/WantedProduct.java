package sk.stuba.fei.uim.oop.assignment3.helper.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.helper.web.WantedProductRequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WantedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productId;
    private int amount;

    public WantedProduct(WantedProductRequest x) {
        this.productId = x.getProductId();
        this.amount = x.getAmount();
    }

}
