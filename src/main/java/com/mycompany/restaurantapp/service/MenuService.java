package com.mycompany.restaurantapp.service;

import com.mycompany.restaurantapp.dto.MenuDTO;
import com.mycompany.restaurantapp.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;

    public MenuDTO getMenuByRestaurantId(Long restaurantId) {
        return modelMapper.map(menuRepository.findByRestaurantId(restaurantId), MenuDTO.class);
    }
}
