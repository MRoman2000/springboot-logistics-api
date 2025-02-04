package logistics_api.controllers;

import logistics_api.model.Order;
import logistics_api.model.Product;
import logistics_api.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getOrder() {
        return orderService.getOrder();
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Order order) {
        try {
            Order saveOrder = orderService.saveOrder(order);
            return ResponseEntity.ok(saveOrder);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(Long id, Order order) {
        try {
            Optional<Order> updateOrder = orderService.updateOrder(id, order);
            return ResponseEntity.ok(updateOrder);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
