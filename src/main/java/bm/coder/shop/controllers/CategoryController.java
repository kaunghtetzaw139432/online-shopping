package bm.coder.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bm.coder.shop.dtos.CategoryDto;
import bm.coder.shop.dtos.Msg;
import bm.coder.shop.models.Category;
import bm.coder.shop.services.CategoryService;
import bm.coder.shop.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/admin/cats")
@AllArgsConstructor
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    private final ImageService imageService;

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping
    public ResponseEntity<Msg> addCat(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setImage(imageService.saveFile(categoryDto.getFile()));
        categoryService.add(category);

        return ResponseEntity.ok(new Msg("Category save success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/{id}")
    public ResponseEntity<Category> singleCat(@PathVariable("id") int id) {
        Category category = categoryService.get(id);
        return ResponseEntity.ok(category);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("/{id}")
    public ResponseEntity<Msg> putCats(@PathVariable int id,  CategoryDto categoryDto) {
        Category cat = new Category();
        cat.setName(categoryDto.getName());
        cat.setImage(imageService.saveFile(categoryDto.getFile()));
        categoryService.patch(id, cat);

        return ResponseEntity.ok(new Msg("Category updated success", HttpStatus.ACCEPTED.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("/name/{id}")
    public ResponseEntity<Msg> getChangeName(@PathVariable int id, @RequestParam String name) {
        Category cat = new Category();
        cat.setName(name);
        categoryService.changeName(id, name);

        return ResponseEntity.ok(new Msg("Change Name success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("/image/{id}")
    public ResponseEntity<Msg> getChangeImage(@PathVariable int id, @RequestPart MultipartFile file) {
        String filename = imageService.saveFile(file);
        categoryService.changeImage(id, filename);

        return ResponseEntity.ok(new Msg("Change Image success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Msg> dropCat(@PathVariable int id) {
        categoryService.drop(id);
        return ResponseEntity.ok(new Msg("Category deleted", HttpStatus.OK.value()));
    }

}
