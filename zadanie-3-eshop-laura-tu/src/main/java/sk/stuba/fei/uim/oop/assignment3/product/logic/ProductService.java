package sk.stuba.fei.uim.oop.assignment3.product.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import sk.stuba.fei.uim.oop.assignment3.helper.web.WantedProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.data.IProductRepository;
import sk.stuba.fei.uim.oop.assignment3.product.data.Product;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.web.bodies.ProductUpdateRequest;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository repository;


    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product create(ProductRequest request) throws NotFoundException {
        Product p = this.repository.save(new Product(request));
        return p;
    }

    @Override
    public Product getById(long id) throws NotFoundException {
        Product p = this.repository.findProductById(id);
        if (p == null) throw new NotFoundException();
        return p;
    }


    @Override
    public Product update(long id, ProductUpdateRequest request) throws NotFoundException {
        Product p = this.getById(id);
        if (request.getName() != null) {
            p.setName(request.getName());
        }
        if (request.getDescription() != null) {
            p.setDescription(request.getDescription());
        }

        return this.repository.save(p);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        Product p = this.getById(id);
        this.repository.delete(p);
    }

    @Override
    public int getAmount(long id) throws NotFoundException {
        return this.getById(id).getAmount();
    }

    @Override
    public int addAmount(long id, int increment) throws NotFoundException {
        Product p = this.getById(id);
        p.setAmount(p.getAmount() + increment);
        this.repository.save(p);
        return p.getAmount();
    }

    @Override
    public void decreaseOnStorage(Product product, WantedProductRequest body) throws IllegalOperationException {
        if (product.getAmount() - body.getAmount() < 0) {
            throw new IllegalOperationException();
        }
        product.setAmount(product.getAmount() - body.getAmount());
        this.repository.save(product);
    }

}
