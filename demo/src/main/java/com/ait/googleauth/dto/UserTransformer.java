package com.ait.googleauth.dto;


import com.ait.googleauth.model.User;
import com.ait.googleauth.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class UserTransformer {

    private final UserService userService;

    public UserTransformer(UserService userService) {
        this.userService = userService;
    }

    public User convertToUpdateEntity(UserDto userDto, BindingResult bindingResult) {
        User user = userService.readById(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        return user;
    }

    public User convertToInsertEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        return user;
    }

    public UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
