package bm.coder.shop.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bm.coder.shop.Exceptions.RoleNotFoundException;
import bm.coder.shop.models.Role;
import bm.coder.shop.repos.RoleRepo;
import bm.coder.shop.services.RoleService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleImpl implements RoleService {
    @Autowired
    private final RoleRepo repo;

    @Override
    public void add(Role role) {
        repo.save(role);
    }

    @Override
    public List<Role> all() {
        return repo.findAll();
    }

    @Override
    public Role get(int id) {
        return repo.findById(id).orElseThrow(() -> new RoleNotFoundException("No roles with that id"));
    }

    @Override
    public void update(Role role, int id) {
        Role role2 = get(id);
        if (role2 != null) {
            role2.setName(role.getName());
            repo.save(role2);
        }
    }

    @Override
    public void drop(int id) {
        Role role2 = get(id);
        if (role2 != null) {
            repo.deleteById(id);
        }
    }

}
