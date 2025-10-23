package bm.coder.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bm.coder.shop.dtos.Msg;
import bm.coder.shop.dtos.SubcatDto;
import bm.coder.shop.models.Subcat;
import bm.coder.shop.services.ImageService;
import bm.coder.shop.services.SubcatService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin/subcats")
@AllArgsConstructor
public class SubcatController {
    @Autowired
    private final SubcatService subcatService;
    @Autowired
    private final ImageService imageService;

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping
    public ResponseEntity<Msg> addCat( SubcatDto subcatDto) {
        Subcat subcat = new Subcat();
        subcat.setName(subcatDto.getName());
        subcat.setImage(imageService.saveFile(subcatDto.getFile()));
        subcatService.add(subcat);

        return ResponseEntity.ok(new Msg("Subcategory save success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/{id}")
    public ResponseEntity<Subcat> singleCat(@PathVariable("id") int id) {
        Subcat subcat = subcatService.get(id);
        return ResponseEntity.ok(subcat);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("/{id}")
    public ResponseEntity<Msg> putCats(@PathVariable int id,  SubcatDto subcatDto) {
        Subcat subcat = new Subcat();
        subcat.setName(subcatDto.getName());
        subcat.setImage(imageService.saveFile(subcatDto.getFile()));
        subcatService.patch(id, subcat);

        return ResponseEntity.ok(new Msg("Subcategory updated success", HttpStatus.ACCEPTED.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("/name/{id}")
    public ResponseEntity<Msg> getChangeName(@PathVariable int id, @RequestParam String name) {
        Subcat subcat = new Subcat();
        subcat.setName(name);
        subcatService.changeName(id, name);

        return ResponseEntity.ok(new Msg("Change Name success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("/image/{id}")
    public ResponseEntity<Msg> getChangeImage(@PathVariable int id, @RequestPart MultipartFile file) {
        String filename = imageService.saveFile(file);
        subcatService.changeImage(id, filename);

        return ResponseEntity.ok(new Msg("Change Image success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Msg> dropCat(@PathVariable int id) {
        subcatService.delete(id);
        ;
        return ResponseEntity.ok(new Msg("Subcategory deleted", HttpStatus.OK.value()));
    }
}
