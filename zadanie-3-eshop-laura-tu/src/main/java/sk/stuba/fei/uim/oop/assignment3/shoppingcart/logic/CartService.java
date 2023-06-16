package sk.stuba.fei.uim.oop.assignment3.shoppingcart.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.helper.data.WantedProduct;
import sk.stuba.fei.uim.oop.assignment3.helper.data.IHelperRepository;
import sk.stuba.fei.uim.oop.assignment3.helper.web.WantedProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.data.IProductRepository;
import sk.stuba.fei.uim.oop.assignment3.product.logic.IProductService;
import sk.stuba.fei.uim.oop.assignment3.shoppingcart.data.Cart;
import sk.stuba.fei.uim.oop.assignment3.shoppingcart.data.ICartRepository;

import java.util.List;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository repository;

    @Autowired
    private IHelperRepository helperRepository;

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductRepository productRepository;


    @Override
    public Cart create() {
        return this.repository.save(new Cart());
    }

    @Override
    public List<Cart> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Cart getById(long id) throws NotFoundException {
        Cart cart = this.repository.findCartById(id);
        if (cart == null) {
            throw new NotFoundException();
        }
        return cart;
    }


    @Override
    public void delete(long id) throws NotFoundException {
        Cart cart = this.getById(id);
        this.repository.delete(cart);
    }

    @Override
    public Cart addToCart(long id, WantedProductRequest body) throws NotFoundException, IllegalOperationException {
        Cart cart = this.getUnpayed(id);
        if (cart.isPayed()) {
            throw new IllegalOperationException();
        }

        WantedProduct helper = this.helperRepository.findHelperByProductId(body.getProductId());
        this.productService.decreaseOnStorage(this.productService.getById(body.getProductId()), body);
        if (!cart.getShoppingList().contains(helper)) {

            helper = new WantedProduct(body);
            cart.getShoppingList().add(helper);

        } else {
            helper.setAmount(helper.getAmount() + body.getAmount());
        }
        this.helperRepository.save(helper);

        this.repository.save(cart);
        return cart;
    }


    @Override
    public double payCart(Long listId) throws NotFoundException, IllegalOperationException {

        Cart cart = this.getUnpayed(listId);

        cart.setPayed(true);
        this.repository.save(cart);

        double priceOfProduct = 0;
        for (WantedProduct h : cart.getShoppingList()) {

            int amount = h.getAmount();
            priceOfProduct += (productRepository.getOne(h.getProductId()).getPrice()) * amount;
        }

        return priceOfProduct;
    }

    private Cart getUnpayed(long id) throws NotFoundException, IllegalOperationException {
        Cart cart = this.getById(id);

        if (cart.isPayed()) {
            throw new IllegalOperationException();
        }
        return cart;
    }
}
