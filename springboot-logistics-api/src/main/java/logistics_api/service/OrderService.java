package logistics_api.service;

import logistics_api.model.Customer;
import logistics_api.model.Order;
import logistics_api.model.Product;
import logistics_api.repository.CustomerRepository;
import logistics_api.repository.OrderRepository;
import logistics_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Order> getOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order saveOrder(Order order) {
        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con ID: " + order.getCustomer().getId()));

        Product product = productRepository.findById(order.getProduct().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado con ID: " + order.getProduct().getId()));

        order.setCustomer(customer);
        order.setProduct(product);

        return orderRepository.save(order);
    }

    public Optional<Order> updateOrder(Long id, Order newOrder) {
        return orderRepository.findById(id).map(existingOrder -> {
            existingOrder.setProduct(newOrder.getProduct());
            existingOrder.setCustomer(newOrder.getCustomer());
            existingOrder.setQuantity(newOrder.getQuantity());
            existingOrder.setTotalPrice(newOrder.getTotalPrice());
            return orderRepository.save(existingOrder);
        });
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order no encontrado: " + id);
        }
        orderRepository.deleteById(id);
    }
}
