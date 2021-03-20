package com.vis.test.service.auth;

import com.vis.test.model.Permission;
import com.vis.test.model.Role;
import com.vis.test.model.User;
import com.vis.test.repository.RoleRepository;
import com.vis.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, ApplicationRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userRepository.findByUsername(username).get();
        } catch (Exception e) {
            throw new UsernameNotFoundException(username);
        }
    }

    /**
     * Method for initializing database with first user: admin/admin with CREATE_USERS permission
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role role = null;
        Optional<Role> roleOptional = roleRepository.findByName("admin");
        if(roleOptional.isEmpty()) {
            role = new Role("admin", List.of(Permission.CREATE_USERS));
            role = roleRepository.save(role);
        } else {
            role = roleOptional.get();
        }
        if(userRepository.findByUsername("admin").isEmpty()) {
            User user = new User("admin", new BCryptPasswordEncoder().encode("admin"), role);
            userRepository.save(user);
        }
    }
}
