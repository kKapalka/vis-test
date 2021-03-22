package com.vis.test.service.auth;

import com.vis.test.model.Role;
import com.vis.test.model.User;
import com.vis.test.repository.RoleRepository;
import com.vis.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final User adminUser = new User("admin", new BCryptPasswordEncoder().encode("admin"), new Role("", new ArrayList<>()));

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            if("admin".equals(username)) {
                return adminUser;
            }
            return userRepository.findByUsername(username).get();
        } catch (Exception e) {
            throw new UsernameNotFoundException(username);
        }
    }
}
