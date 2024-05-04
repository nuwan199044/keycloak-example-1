package com.mycompany.restaurantapp.controller;

import com.mycompany.restaurantapp.dto.MenuDTO;
import com.mycompany.restaurantapp.dto.MenuItemDTO;
import com.mycompany.restaurantapp.dto.RestaurantDTO;
import com.mycompany.restaurantapp.service.MenuItemService;
import com.mycompany.restaurantapp.service.MenuService;
import com.mycompany.restaurantapp.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final MenuService menuService;
    private final MenuItemService menuItemService;

    @GetMapping("/public/list")
    public List<RestaurantDTO> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("/public/menu/{restaurantId}")
    public MenuDTO getRestaurants(@PathVariable Long restaurantId) {
        return menuService.getMenuByRestaurantId(restaurantId);
    }

    @PostMapping
    public RestaurantDTO createRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.createRestaurant(restaurantDTO);
    }

    @PutMapping("/menu/item/{itemId}/{price}")
    public MenuItemDTO createRestaurant(@PathVariable Long itemId, @PathVariable Double price) {
        return menuItemService.updateMenuItemPrice(itemId, price);
    }
}
