package nl.devc0n.OrderManagement.service;

import nl.devc0n.OrderManagement.model.Order;
import nl.devc0n.OrderManagement.model.OrderStatus;
import nl.devc0n.OrderManagement.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static nl.devc0n.OrderManagement.model.OrderStatus.PLACED;
import static nl.devc0n.OrderManagement.model.OrderStatus.SHIPPED;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order placeOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(PLACED);
        return orderRepository.save(order);
    }

    // Get an order by ID
    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Update order status
    public Optional<Order> updateOrderStatus(Long id, OrderStatus status) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(status);
            if (status == SHIPPED) {
                order.setShippedDate(LocalDateTime.now());
            }
            orderRepository.save(order);
        }
        return orderOptional;
    }

}
