package com.mycompany.restaurantapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuDTO implements Serializable {
    private Long id;
    private String name;
    private List<MenuItemDTO> items;
}
