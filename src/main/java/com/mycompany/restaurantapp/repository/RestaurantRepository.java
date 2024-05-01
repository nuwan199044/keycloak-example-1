package com.mycompany.restaurantapp.repository;

import com.mycompany.restaurantapp.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
