package bm.coder.shop.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import bm.coder.shop.models.AppUser;
import bm.coder.shop.models.Role;

@Service
public interface UserService {
    void register(AppUser user);

    void addRole(Long userId, int roleId);

    AppUser findByName(String name);

    void removeRole(Long userId, int roleId);

    List<AppUser> all();

    AppUser get(Long id);

    Set<Role> getUserRole(Long userId);

}
