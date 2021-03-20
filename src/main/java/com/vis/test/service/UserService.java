package com.vis.test.service;

import com.vis.test.dto.UserDto;
import com.vis.test.model.User;
import com.vis.test.repository.RoleRepository;
import com.vis.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User modifyUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return persistAndReturnUser(userDto, user, false);
    }

    public User saveUser(UserDto userDto) {
        if(userRepository.existsByUsername(userDto.getUsername())) {
            throw new EntityExistsException("User with such username already exists");
        }
        return persistAndReturnUser(userDto, new User(), true);
    }

    private User persistAndReturnUser(UserDto userDto, User persistedUser, boolean modifyPassword) {
        persistedUser.setUsername(userDto.getUsername());
        if(modifyPassword) {
            persistedUser.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        }
        persistedUser.setRole(roleRepository.findById(userDto.getRoleId()).orElseThrow(EntityNotFoundException::new));
        return userRepository.save(persistedUser);
    }
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
