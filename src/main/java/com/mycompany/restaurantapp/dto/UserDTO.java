package com.mycompany.restaurantapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    protected String id;
    protected String username;
    protected String firstName;
    protected String lastName;
    protected String email;
    protected Boolean emailVerified;
}
