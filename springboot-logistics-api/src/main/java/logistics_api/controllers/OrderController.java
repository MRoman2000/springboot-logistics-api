package logistics_api.controllers;

import logistics_api.model.Order;
import logistics_api.model.Product;
import logistics_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Order saveProduct(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }
}
