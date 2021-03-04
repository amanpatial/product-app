package com.product.service;
import com.product.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();
    public Product getProduct(Long id);
    public Product addProduct(Product product);
    public Product updateProduct(Product product, Long id);
    public void deleteProduct(Long id);
    public List<Product> findByName(String name);
    public List<Product> getMostViewedProducts(int maxLimit);
}
