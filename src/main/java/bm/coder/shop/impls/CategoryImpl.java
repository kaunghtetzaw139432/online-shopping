package bm.coder.shop.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bm.coder.shop.Exceptions.CategoryNotFoundException;
import bm.coder.shop.models.Category;
import bm.coder.shop.repos.CategoryRepo;
import bm.coder.shop.services.CategoryService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryImpl implements CategoryService {
    @Autowired
    private final CategoryRepo categoryRepo;

    @Override
    public Category add(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> all() {
        return categoryRepo.findAll();
    }

    @Override
    public Category get(int id) {
        return categoryRepo.findById(id).orElseThrow(() -> new CategoryNotFoundException("No category with that id"));
    }

    @Override
    public void drop(int id) {
        Category cat = get(id);
        if (cat != null) {
            categoryRepo.deleteById(id);
        }
    }

    @Override
    public Category patch(int id, Category category) {
        Category dbcat = get(id);
        if (dbcat != null) {
            dbcat.setName(category.getName());
            dbcat.setImage(category.getImage());
           return   categoryRepo.save(dbcat);
        }
        return null;
      
    }

    @Override
    public Category changeName(int id, String name) {
        Category dbcat = get(id);
        if (dbcat != null) {
            dbcat.setName(name);
            categoryRepo.save(dbcat);
        }
        return null;
    }

    @Override
    public Category changeImage(int id, String image) {
        Category dbcat = get(id);
        if (dbcat != null) {
            dbcat.setName(image);
            categoryRepo.save(dbcat);
        }
        return null;
    }

}
