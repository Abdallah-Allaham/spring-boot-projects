package com.abdallah.order_api.service;

import com.abdallah.order_api.model.Customer;
import com.abdallah.order_api.model.Order;
import com.abdallah.order_api.model.OrderItem;
import com.abdallah.order_api.model.Product;
import com.abdallah.order_api.repo.CustomerRepo;
import com.abdallah.order_api.repo.OrderRepo;
import com.abdallah.order_api.repo.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final ProductRepo productRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, CustomerRepo customerRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
    }

    @Transactional
    public Order createOrder(Long customerId, Map<Long,Integer> productIdsWithQuantities){
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setTotalAmount(0.0);

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount=0.0;

        for (Map.Entry<Long,Integer> entry : productIdsWithQuantities.entrySet()){
            Long productId = entry.getKey();
            Integer quantity = entry.getValue();

            if(quantity <= 0){
                throw new RuntimeException();
            }

            Product product = productRepo.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getQuantity() < quantity){
                throw new RuntimeException();
            }

            product.setQuantity(product.getQuantity()-quantity);
            productRepo.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(1);
            orderItem.setPrice(product.getPrice());

            orderItems.add(orderItem);
            totalAmount += orderItem.getPrice() * orderItem.getQuantity();
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        return orderRepo.save(order);
    }

    public Optional<Order> getOrderById(Long id){
        return orderRepo.findById(id);
    }

    public List<Order> getAllOrders(){
        return orderRepo.findAll();
    }

    public void deleteOrder(Long id){
        orderRepo.deleteById(id);
    }

    public List<Order> getOrdersBuCustomerId(Long id){
        return orderRepo.findByCustomerId(id);
    }

    public Optional<Order> updateOrderStatus(Long id ,String newStatus){
        Optional<Order> orderOptional = orderRepo.findById(id);
        if (orderOptional.isPresent()){
            Order order = orderOptional.get();
            order.setStatus(newStatus);
            return Optional.of(orderRepo.save(order));
        }

        return Optional.empty();
    }
}
