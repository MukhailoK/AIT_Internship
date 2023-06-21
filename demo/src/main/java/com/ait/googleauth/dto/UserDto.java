package com.ait.googleauth.dto;


import com.ait.googleauth.model.Role;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @Id
    private Long id;
    private String name;
    private String email;
    private Role role;

    public boolean isNew() {
        return this.id == null;
    }

}
