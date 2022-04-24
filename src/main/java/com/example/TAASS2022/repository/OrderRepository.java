package com.example.TAASS2022.repository;

import com.example.TAASS2022.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
