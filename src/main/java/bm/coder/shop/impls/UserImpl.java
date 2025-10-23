package bm.coder.shop.impls;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bm.coder.shop.Exceptions.RoleNotFoundException;
import bm.coder.shop.Exceptions.UserNotFoundException;
import bm.coder.shop.models.AppUser;
import bm.coder.shop.models.Role;
import bm.coder.shop.repos.RoleRepo;
import bm.coder.shop.repos.UserRepo;
import bm.coder.shop.services.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserImpl implements UserService {
    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final RoleRepo Rolerepo;

    @Override
    public void register(AppUser user) {
        userRepo.save(user);
    }

    @Override
    public void addRole(Long userId, int roleId) {
        AppUser user = get(userId);
        Role role = getRole(roleId);

        if (user != null && role != null) {
            Set<Role> roles = user.getRoles();
            roles.add(role);
            user.setRoles(roles);
            userRepo.save(user);
        }

    }

    private Role getRole(int roleId) {
        return Rolerepo.findById(roleId).orElseThrow(() -> new RoleNotFoundException("No role with that id"));
    }

    @Override
    public AppUser get(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("no user with that id"));
    }

    @Override  
    public List<AppUser> all() {
        return userRepo.findAll();
    }

    @Override
    public void removeRole(Long userId, int roleId) {
        AppUser user = get(userId);
        Role role = getRole(roleId);

        if (user != null && role != null) {
            Set<Role> roles = user.getRoles();
            roles.remove(role);
            user.setRoles(roles);
            userRepo.save(user);
        }
    }

    @Override
    public Set<Role> getUserRole(Long userId) {
        AppUser user = get(userId);
        return user.getRoles();
    }

    @Override
    public AppUser findByName(String name) {
        return userRepo.findByName(name);
    }
}
