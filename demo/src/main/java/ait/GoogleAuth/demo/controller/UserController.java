package ait.GoogleAuth.demo.controller;


import ait.GoogleAuth.demo.dto.UserDto;
import ait.GoogleAuth.demo.dto.UserResponse;
import ait.GoogleAuth.demo.dto.UserTransformer;
import ait.GoogleAuth.demo.model.User;
import ait.GoogleAuth.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final UserTransformer userTransformer;

    public UserController(UserService userService, UserTransformer userTransformer) {
        this.userService = userService;
        this.userTransformer = userTransformer;
    }


    @GetMapping(value = "/user/{id}")
    public UserDto findOne(@PathVariable long id) {
        User entity = userService.readById(id);
        return userTransformer.convertToDto(entity);
    }

    @GetMapping(value = "/all")
    List<UserResponse> getAll() {
        return userService.getAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }
}
