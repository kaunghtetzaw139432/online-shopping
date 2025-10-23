package bm.coder.shop.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bm.coder.shop.Exceptions.UserNotFoundException;
import bm.coder.shop.models.AppUser;
import bm.coder.shop.repos.UserRepo;

@Service

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByName(username);
        if (user == null) {
            throw new UserNotFoundException("No user with that name");
        }
        Set<GrantedAuthority> authorities = user.getRoles()
                .stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }

}
