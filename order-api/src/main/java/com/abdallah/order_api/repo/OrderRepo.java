package com.abdallah.order_api.repo;

import com.abdallah.order_api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {


    List<Order> findByCustomerId(Long customerId);
}
