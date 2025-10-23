package bm.coder.shop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import bm.coder.shop.models.Product;

@Service
public interface ProductService {
    void add(Product product);

    List<Product> all();

    Product get(Long id);

    void update(Long id, Product product);

    void drop(Long id);

    List<Product> byCatId(int id);

    List<Product> bySubcatId(int id);

    List<Product> byChildcatId(int id);

    List<Product> byTagId(int id);
}
