package logistics_api.service;

import logistics_api.model.Customer;
import logistics_api.model.Product;
import logistics_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {

        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }


    public void deleteProduct(Integer id) {

        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado: " + id);
        }
        productRepository.deleteById(id);
    }
    /*
    public Product updateProduct(Integer id, Product products) {
        return productRepository.findById(id).map(product -> {
            product.setName(products.getName());
            product.setPrice(products.getPrice());
            product.setQuantity(products.getQuantity());
            return productRepository.save(product);

        }).orElseThrow(() -> new RuntimeException("Cliente no encontrado: "));
    } */

    public Optional<Product> updateProduct(Integer id, Product newProduct) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(newProduct.getName());
            existingProduct.setQuantity(newProduct.getQuantity());
            existingProduct.setPrice(newProduct.getPrice());
            return productRepository.save(existingProduct);
        });
    }

    public Product pathcProduct(Integer id, Product product) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
        Product patchProduct = optionalProduct.get();
        if (product.getName() != null) {
            patchProduct.setName(product.getName());
        }
        if (product.getPrice() != 0) {
            patchProduct.setPrice(product.getPrice());
        }
        if (product.getQuantity() != 0) {
            patchProduct.setQuantity(product.getQuantity());
        }
        return productRepository.save(patchProduct);
    }

}
