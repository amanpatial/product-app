package com.product.service;
import com.product.model.Product;
import com.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }
    public Product getProduct(Long id){
        Product product = productRepository.getOne(id);
        if (product != null) {
            product.setViews();
            productRepository.save(product);
        }
        return product;
    }

    public List<Product> getMostViewedProducts(int maxLimit){
        List<Product> products = productRepository.findAll();
        return findMaxViewed(products, maxLimit);
    }

    private List<Product> findMaxViewed(List<Product> products, int maxLimit) {
        return products.stream().sorted(Comparator.comparing(Product::getViews).reversed())
                .limit(maxLimit)
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product product, Long id){
        return productRepository.save(product);
    }

    public Product addProduct(Product product) { return productRepository.save(product); }

    public List<Product> findByName(String name) { return productRepository.findByName(name);}
}
