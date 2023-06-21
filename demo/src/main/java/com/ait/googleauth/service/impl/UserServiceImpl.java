package com.ait.googleauth.service.impl;

import com.ait.googleauth.model.Role;
import com.ait.googleauth.model.User;
import com.ait.googleauth.repository.UserRepository;
import com.ait.googleauth.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User readById(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found")
        );
    }

    @Override
    public User update(User user) {
        if (user.getRole() == Role.ROLE_ADMIN) {
            readById(user.getId());
            return userRepository.save(user);
        }
        throw new NullPointerException("User can't be 'null'");
    }

    @Override
    public void delete(long id) {
        userRepository.delete(readById(id));
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

}
