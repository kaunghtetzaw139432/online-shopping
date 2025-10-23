package bm.coder.shop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import bm.coder.shop.models.Category;

@Service
public interface CategoryService {
    Category add(Category category);

    List<Category> all();

    Category get(int id);

    void drop(int id);

    Category patch(int id, Category category);

    Category changeName(int id, String name);

    Category changeImage(int id, String image);
}
