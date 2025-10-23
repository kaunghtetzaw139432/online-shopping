package bm.coder.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bm.coder.shop.dtos.Msg;
import bm.coder.shop.models.Role;
import bm.coder.shop.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/admin/roles")
@AllArgsConstructor
public class RoleController {
    @Autowired
    private final RoleService roleService;

    @CrossOrigin(origins = "http://localhost:4000")
    @PostMapping
    public ResponseEntity<Msg> addRole(String name) {
        Role role = new Role();
        role.setName(name);
        roleService.add(role);

        return ResponseEntity.ok(new Msg("Role added successfully", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping
    public ResponseEntity<List<Role>> allRole() {
        List<Role> roles = roleService.all();
        return ResponseEntity.ok(roles);
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @GetMapping("/{id}")
    public ResponseEntity<Role> singlerole(@PathVariable int id) {
        return ResponseEntity.ok(roleService.get(id));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @PutMapping("/{id}")
    public ResponseEntity<Msg> updateRole(@PathVariable int id, String name) {
        Role role = new Role();
        role.setName(name);
        roleService.update(role, id);

        return ResponseEntity.ok(new Msg("Role update successfully", HttpStatus.OK.value()));
    }

    @CrossOrigin(origins = "http://localhost:4000")
    @DeleteMapping
    public ResponseEntity<Msg> updateRole(@PathVariable int id) {
        roleService.drop(id);
        return ResponseEntity.ok(new Msg("Role delete successfully", HttpStatus.OK.value()));
    }

}
