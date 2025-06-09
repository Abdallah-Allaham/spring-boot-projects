package com.abdallah.order_api.controller;

import com.abdallah.order_api.model.Order;
import com.abdallah.order_api.service.OrderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity :: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrder createOrder){
        if (createOrder.getCustomerId() == null ||
                createOrder.getProductWithQuantities().isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        try {
            Order order =orderService.createOrder(createOrder.getCustomerId(),createOrder.getProductWithQuantities());
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersById(@PathVariable Long customerId){
        List<Order> orders = orderService.getOrdersBuCustomerId(customerId);
        if (orders.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> changeOrderStatus(@PathVariable Long id,@RequestParam String newStatus){
        Optional<Order> order = orderService.updateOrderStatus(id,newStatus);
        return order.map(ResponseEntity :: ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

//DTO
@Data
class CreateOrder{
    private Long customerId;
    private Map<Long,Integer> productWithQuantities;

}