package com.mycompany.restaurantapp.repository;

import com.mycompany.restaurantapp.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
