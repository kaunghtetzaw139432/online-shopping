package bm.coder.shop.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private List<String> images;
    
    private double price;

    private String description;

    @ManyToOne
    Category category;

    @ManyToOne
    Subcat subcat;

    @ManyToOne
    Childcat childcat;

    @ManyToOne
    Tag tag;
}
