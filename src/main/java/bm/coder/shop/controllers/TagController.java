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
import org.springframework.web.bind.annotation.RestController;

import bm.coder.shop.dtos.Msg;
import bm.coder.shop.dtos.TagDto;
import bm.coder.shop.models.Tag;
import bm.coder.shop.services.ImageService;
import bm.coder.shop.services.TagService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin/tags")
@AllArgsConstructor
public class TagController {
    @Autowired
    private final TagService tagService;
    @Autowired
    private final ImageService imageService;

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping
    public ResponseEntity<Msg> addTag(TagDto tagDto) {
        Tag tag = new Tag();
        tag.setName(tagDto.getName());
        tag.setImage(imageService.saveFile(tagDto.getFile()));
        tagService.add(tag);

        return ResponseEntity.ok(new Msg("tag save success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/{id}")
    public ResponseEntity<Tag> singleCat(@PathVariable("id") int id) {
        Tag tag = tagService.get(id);
        return ResponseEntity.ok(tag);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("/{id}")
    public ResponseEntity<Msg> putCats(@PathVariable int id, TagDto tagDto) {
        Tag tag = new Tag();
        tag.setName(tagDto.getName());
        tag.setImage(imageService.saveFile(tagDto.getFile()));
        tagService.update(id, tag);

        return ResponseEntity.ok(new Msg("tag updated success", HttpStatus.ACCEPTED.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("/name/{id}")
    public ResponseEntity<Msg> getChangeName(@PathVariable int id, @RequestParam String name) {
        Tag tag = new Tag();
        tag.setName(name);
        tagService.changeName(id, name);

        return ResponseEntity.ok(new Msg("Change Name success", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Msg> dropCat(@PathVariable int id) {
        tagService.drop(id);
        return ResponseEntity.ok(new Msg("tag deleted", HttpStatus.OK.value()));
    }
}
