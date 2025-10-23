package bm.coder.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bm.coder.shop.dtos.Msg;
import bm.coder.shop.models.AppUser;
import bm.coder.shop.services.UserService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/admin/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping
    public ResponseEntity<List<AppUser>> allUsers() {
        List<AppUser> users = userService.all();
        return ResponseEntity.ok(users);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("add/role/{userId}/{roleId}")
    public ResponseEntity<Msg> addRole(@PathVariable Long userId, @PathVariable int roleId) {
        userService.addRole(userId, roleId);

        return ResponseEntity.ok(new Msg("Role added successfully", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("remove/role/{userId}/{roleId}")
    public ResponseEntity<Msg> removeRole(@PathVariable Long userId, @PathVariable int roleId) {
        userService.removeRole(userId, roleId);

        return ResponseEntity.ok(new Msg("Role added successfully", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> get(@PathVariable Long id) {
        AppUser user = userService.get(id);
        return ResponseEntity.ok(user);
    }

}
