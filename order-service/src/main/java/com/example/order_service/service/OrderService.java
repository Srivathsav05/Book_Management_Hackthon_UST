package com.example.order_service.service;
import com.example.order_service.entity.Order;
import com.example.order_service.exception.OrderNotFound;
import com.example.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public Order getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new OrderNotFound("Order not found with id: " + id);
        }
    }
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
    public Order updateOrder(Order order) {
        Optional<Order> existingOrder = orderRepository.findById(order.getId());
        if (existingOrder.isPresent()) {
            existingOrder.get().setCustomerId(order.getCustomerId());
            existingOrder.get().setBookId(order.getBookId());
            existingOrder.get().setQuantity(order.getQuantity());
            existingOrder.get().setStatus(order.getStatus());
            return orderRepository.save(existingOrder.get());
        } else {
            throw new OrderNotFound("Order not found with id: " + order.getId());

        }
    }
    public void deleteOrder(Long id) {
        Optional<Order> existingOrder = orderRepository.findById(id);
        if (existingOrder.isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new OrderNotFound("Order not found with id: " + id);
        }
    }

}
