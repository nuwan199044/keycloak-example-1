package com.mycompany.restaurantapp.service;

import com.mycompany.restaurantapp.dto.MenuItemDTO;
import com.mycompany.restaurantapp.entity.MenuItem;
import com.mycompany.restaurantapp.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final ModelMapper modelMapper;

    public MenuItemDTO updateMenuItemPrice(Long menuItemId, Double newPrice) {
        Optional<MenuItem> optionalMenuItem = menuItemRepository.findById(menuItemId);
        return optionalMenuItem.map(menuItem -> {
            menuItem.setPrice(newPrice);
            return modelMapper.map(menuItemRepository.save(menuItem), MenuItemDTO.class);
        }).orElse(null);
    }
}
