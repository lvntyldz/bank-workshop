package com.ba.controller;

import com.ba.domain.Customer;
import com.ba.domain.Order;
import com.ba.repository.CustomerRepository;
import com.ba.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/add")
    public Set<Order> addOrder() {
        Customer customer = new Customer(null, "ayşe");
        customerRepository.save(customer);

        Order order1 = new Order(null, "kulaklık", 470, customer);
        orderRepository.save(order1);

        Order order2 = new Order(null, "kulaklık", 470, customer);
        orderRepository.save(order2);

        return customer.getOrders();
    }

    @GetMapping("/list")
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (!optionalOrder.isPresent()) {
            return null;
        }

        Order order = optionalOrder.get();

        return order;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "ID : " + id + " olan kitap ve sayfaları silindi";
    }
}
