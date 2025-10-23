package bm.coder.shop.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bm.coder.shop.Exceptions.ProductNotFoundException;
import bm.coder.shop.models.Product;
import bm.coder.shop.repos.ProductRepo;
import bm.coder.shop.services.ProductService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductImpl implements ProductService {
    @Autowired
    private final ProductRepo productRepo;

    @Override
    public void add(Product product) {
        productRepo.save(product);
    }

    @Override
    public List<Product> all() {
        return productRepo.findAll();
    }

    @Override
    public Product get(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("No product with that id"));
    }

    @Override
    public void update(Long id, Product product) {
        Product dbProduct = get(id);
        if (dbProduct != null) {
            dbProduct.setName(product.getName());
            dbProduct.setImages(product.getImages());
            dbProduct.setDescription(product.getDescription());
            dbProduct.setCategory(product.getCategory());
            dbProduct.setChildcat(product.getChildcat());
            dbProduct.setSubcat(product.getSubcat());
            dbProduct.setPrice(product.getPrice());
            dbProduct.setTag(product.getTag());
            productRepo.save(dbProduct);
        }
    }

    @Override
    public void drop(Long id) {
        Product dbProduct = get(id);
        if (dbProduct != null) {
            productRepo.deleteById(id);
        }
    }

    @Override
    public List<Product> byCatId(int id) {
        return productRepo.findByCategoryId(id);

    }

    @Override
    public List<Product> bySubcatId(int id) {
        return productRepo.findBySubcatId(id);
    }

    @Override
    public List<Product> byChildcatId(int id) {
        return productRepo.findByChildcatId(id);
    }

    @Override
    public List<Product> byTagId(int id) {
        return productRepo.findByTagId(id);
    }
}
