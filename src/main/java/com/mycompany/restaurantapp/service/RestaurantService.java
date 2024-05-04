package com.mycompany.restaurantapp.service;

import com.mycompany.restaurantapp.dto.RestaurantDTO;
import com.mycompany.restaurantapp.entity.Restaurant;
import com.mycompany.restaurantapp.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        return modelMapper.map(restaurantRepository.save(modelMapper.map(restaurantDTO, Restaurant.class)), RestaurantDTO.class);
    }

    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO) {
        return restaurantRepository.findById(restaurantDTO.getId())
                .map(restaurant -> {
                    modelMapper.map(restaurantDTO, restaurant);
                    return restaurantRepository.save(restaurant);
                })
                .map(updatedRestaurant -> modelMapper.map(updatedRestaurant, RestaurantDTO.class))
                .orElse(null);
    }

    public List<RestaurantDTO> getRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDTO.class))
                .toList();
    }
}
