package com.seung.order_api.domain.repository;

import com.seung.order_api.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
