package com.example.order_service.controller;

import com.example.order_service.clients.BookServiceClient;
import com.example.order_service.clients.CustomerServiceClient;
import com.example.order_service.converter.OrderDtoConverter;
import com.example.order_service.dto.BookDto;
import com.example.order_service.dto.OrderDto;
import com.example.order_service.entity.Order;
import com.example.order_service.exception.OutOfStockException;
import com.example.order_service.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderDtoConverter orderDtoConverter;
    private final BookServiceClient bookServiceClient;
    private final CustomerServiceClient customerServiceClient;
    public OrderController(OrderService orderService, OrderDtoConverter orderDtoConverter, BookServiceClient bookServiceClient, CustomerServiceClient customerServiceClient) {
        this.orderService = orderService;
        this.orderDtoConverter = orderDtoConverter;
        this.bookServiceClient = bookServiceClient;
        this.customerServiceClient = customerServiceClient;
    }

//    @GetMapping
//  public ResponseEntity<List<Order>> getAllOrders() {
//        return ResponseEntity.ok(orderService.getAllOrders());
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Order> updateOrder(@PathVariable long id, @RequestBody OrderDto dto) {
//        return ResponseEntity.ok(orderService.updateOrder(orderDtoConverter.convertToEntity(dto)));
//    }
//    @DeleteMapping("/{id}")
//    public void deleteOrder(@PathVariable long id) {
//        orderService.deleteOrder(id);
//    }
    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable long id) {
        Order order = orderService.getOrderById(id);
        OrderDto dto = new OrderDto(
                order.getId(),
               customerServiceClient.getCustomerById(order.getCustomerId()),
                bookServiceClient.getBookById(order.getBookId()),
                order.getQuantity(),
                order.getStatus()
        );
        return dto;

    }
    @PostMapping
    public OrderDto createOrder(Order order){
        Order order1 = new Order(
                order.getId(),
                order.getCustomerId(),
                order.getBookId(),
                order.getQuantity(),
                order.getStatus()
        );
        BookDto bookDto = bookServiceClient.getBookById(order.getBookId());
        if (bookDto.stock() < order.getQuantity()) {
            throw new OutOfStockException("Not enough stock");
        }
        order1 = orderService.createOrder(order1);
        OrderDto dto = new OrderDto(
                order1.getId(),
                customerServiceClient.getCustomerById(order1.getCustomerId()),
                bookServiceClient.getBookById(order1.getBookId()),
                order1.getQuantity(),
                order1.getStatus()
        );
        return dto;
    }
}
