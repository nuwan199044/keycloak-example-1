package com.mycompany.restaurantapp.repository;

import com.mycompany.restaurantapp.dto.MenuDTO;
import com.mycompany.restaurantapp.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByRestaurantId(Long restaurantId);
}
