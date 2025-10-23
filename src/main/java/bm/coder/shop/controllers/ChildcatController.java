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

import bm.coder.shop.dtos.ChildcatDto;
import bm.coder.shop.dtos.Msg;
import bm.coder.shop.models.Childcat;
import bm.coder.shop.services.ChildcatService;
import bm.coder.shop.services.ImageService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin/childcats")
@AllArgsConstructor
public class ChildcatController {
    @Autowired
    private final ChildcatService childcatService;
    @Autowired
    private final ImageService imageService;

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping
    public ResponseEntity<Msg> addCat( ChildcatDto childcatDto) {
        Childcat childcat = new Childcat();
        childcat.setName(childcatDto.getName());
        childcat.setImage(imageService.saveFile(childcatDto.getFile()));
        childcatService.add(childcat);

        return ResponseEntity.ok(new Msg("Childcategory save success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/{id}")
    public ResponseEntity<Childcat> singleCat(@PathVariable("id") int id) {
        Childcat childcat = childcatService.get(id);
        return ResponseEntity.ok(childcat);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("/{id}")
    public ResponseEntity<Msg> putCats(@PathVariable int id, ChildcatDto childcatDto) {
        Childcat childcat = new Childcat();
        childcat.setName(childcat.getName());
        childcat.setImage(imageService.saveFile(childcatDto.getFile()));
        childcatService.patch(id, childcat);

        return ResponseEntity.ok(new Msg("Childcategory updated success", HttpStatus.ACCEPTED.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("/name/{id}")
    public ResponseEntity<Msg> getChangeName(@PathVariable int id, @RequestParam String name) {
        Childcat childcat = new Childcat();
        childcat.setName(name);
        childcatService.changeName(id, name);

        return ResponseEntity.ok(new Msg("Change Name success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("/image/{id}")
    public ResponseEntity<Msg> getChangeImage(@PathVariable int id, @RequestPart MultipartFile file) {
        String filename = imageService.saveFile(file);
        childcatService.changeImage(id, filename);

        return ResponseEntity.ok(new Msg("Change Image success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Msg> dropCat(@PathVariable int id) {
        childcatService.delete(id);
        ;
        return ResponseEntity.ok(new Msg("Childcategory deleted", HttpStatus.OK.value()));
    }
}
