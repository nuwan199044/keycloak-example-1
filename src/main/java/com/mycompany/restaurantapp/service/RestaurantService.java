package com.mycompany.restaurantapp.service;

import com.mycompany.restaurantapp.dto.RestaurantDTO;
import com.mycompany.restaurantapp.entity.Restaurant;
import com.mycompany.restaurantapp.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        return modelMapper.map(restaurantRepository.save(modelMapper.map(restaurantDTO, Restaurant.class)), RestaurantDTO.class);
    }

    public List<RestaurantDTO> getRestaurants(){
        return restaurantRepository.findAll()
                .stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class))
                .toList();
    }
}
