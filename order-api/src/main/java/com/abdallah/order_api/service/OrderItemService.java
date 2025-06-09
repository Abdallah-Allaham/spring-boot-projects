package com.abdallah.order_api.service;

import com.abdallah.order_api.model.OrderItem;
import com.abdallah.order_api.repo.OrderItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepo orderItemRepo;

    @Autowired
    public OrderItemService(OrderItemRepo orderItemRepo) {
        this.orderItemRepo = orderItemRepo;
    }

    public OrderItem createOrderItem(OrderItem orderItem){
        return orderItemRepo.save(orderItem);
    }

    public Optional<OrderItem> getOrderItemById(Long id){
        return orderItemRepo.findById(id);
    }

    public List<OrderItem> getAllOrderItems(){
        return orderItemRepo.findAll();
    }

    public void deleteOrderItem(Long id){
        orderItemRepo.deleteById(id);
    }

    public List<OrderItem> getOrderItemsByOrderId(Long id){
        return orderItemRepo.findByOrderId(id);
    }

}
