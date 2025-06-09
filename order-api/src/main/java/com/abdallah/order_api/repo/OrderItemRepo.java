package com.abdallah.order_api.repo;

import com.abdallah.order_api.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {


    List<OrderItem> findByOrderId(Long orderId);
}
