package sk.stuba.fei.uim.oop.assignment3.shoppingcart.data;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.helper.data.WantedProduct;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<WantedProduct> shoppingList;

    private boolean payed;

    public Cart() {
        this.shoppingList = new ArrayList<>();
    }


}
