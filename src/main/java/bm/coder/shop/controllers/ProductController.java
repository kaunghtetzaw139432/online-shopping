package bm.coder.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bm.coder.shop.dtos.Msg;
import bm.coder.shop.dtos.ProductDto;
import bm.coder.shop.models.Product;
import bm.coder.shop.services.CategoryService;
import bm.coder.shop.services.ChildcatService;
import bm.coder.shop.services.ImageService;
import bm.coder.shop.services.ProductService;
import bm.coder.shop.services.SubcatService;
import bm.coder.shop.services.TagService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/admin/products")
@AllArgsConstructor
public class ProductController {
    @Autowired
    private final ProductService productService;

    @Autowired
    private final CategoryService categoryService;

    @Autowired
    private final SubcatService subcatService;

    @Autowired
    private final ChildcatService childcatService;

    @Autowired
    private final TagService tagService;

    @Autowired
    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<List<Product>> all() {
        List<Product> products = productService.all();
        return ResponseEntity.ok(products);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping
    public ResponseEntity<Msg> addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(categoryService.get(productDto.getCatId()));
        product.setChildcat(childcatService.get(productDto.getChildcatId()));
        product.setSubcat(subcatService.get(productDto.getSubcatId()));
        product.setTag(tagService.get(productDto.getTagId()));
        product.setImages(imageService.saveFiles(productDto.getFiles()));
        productService.add(product);

        return ResponseEntity.ok(new Msg("Product save success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("/{id}")
    public ResponseEntity<Msg> updateProduct(@PathVariable Long id, ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(categoryService.get(productDto.getCatId()));
        product.setChildcat(childcatService.get(productDto.getChildcatId()));
        product.setSubcat(subcatService.get(productDto.getSubcatId()));
        product.setTag(tagService.get(productDto.getTagId()));
        product.setImages(imageService.saveFiles(productDto.getFiles()));
        productService.update(id, product);

        return ResponseEntity.ok(new Msg("Product update success", HttpStatus.OK.value()));

    }

    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Msg> deleteProduct(@PathVariable Long id) {
        productService.drop(id);
        return ResponseEntity.ok(new Msg("Product delete success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> catProdcut(@PathVariable int id) {
        List<Product> products = productService.byCatId(id);
        return ResponseEntity.ok(products);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/subcat/{id}")
    public ResponseEntity<List<Product>> ssubcatProdcut(@PathVariable int id) {
        List<Product> products = productService.bySubcatId(id);
        return ResponseEntity.ok(products);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/childcat/{id}")
    public ResponseEntity<List<Product>> childcatProdcut(@PathVariable int id) {
        List<Product> products = productService.byChildcatId(id);
        return ResponseEntity.ok(products);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/tag/{id}")
    public ResponseEntity<List<Product>> TagProdcut(@PathVariable int id) {
        List<Product> products = productService.byTagId(id);
        return ResponseEntity.ok(products);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/{id}")
    public ResponseEntity<Product> singleProdcut(@PathVariable Long id) {
        Product product = productService.get(id);
        return ResponseEntity.ok(product);
    }

}
