package com.mycompany.restaurantapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantDTO implements Serializable {
    private Long id;
    private String name;
    private String location;
}
