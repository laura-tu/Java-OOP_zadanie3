package sk.stuba.fei.uim.oop.assignment3.product.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private int amount;
    private String unit;
    private double price;

    public Product(ProductRequest req) {
        this.name = req.getName();
        this.description = req.getDescription();
        this.amount = req.getAmount();
        this.unit = req.getUnit();
        this.price = req.getPrice();
    }

}
