package com.mycompany.restaurantapp.repository;

import com.mycompany.restaurantapp.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
