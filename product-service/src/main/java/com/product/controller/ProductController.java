package com.product.controller;

import com.product.dto.ProductDto;
import com.product.exception.ProductAlreadyExistException;
import com.product.exception.ResourceNotFoundException;
import com.product.model.Product;
import com.product.service.ProductMapper;
import com.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * REST endpoints
 *
 * @author aman
 */

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private int maxTopViewLimit = 5;

    // autowire the ProductService class
    @Autowired
    private ProductService productService;

    @RequestMapping("/hello")
    public String getName() {
        String returnValue = "Hello";
        LOGGER.info("{} ", returnValue);
        return returnValue;
    }

    // Get All Products
    // create a Request Mapping that retrieves all the products detail from the
    // database
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts() {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<List<Product>>(productService.getAllProducts(), responseHeaders, HttpStatus.OK);
    }

    // Get Single Product
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) throws ResourceNotFoundException {
        Product product = productService.getProduct(id);
        ProductDto productDto = new ProductDto();
        System.out.print("Product info:" + product.toString());
        if (product != null) {
            productDto = ProductMapper.productEntityToProductDto(product);
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            // return new ResponseEntity<>(productDto, HttpStatus.NOT_FOUND);
            throw new ResourceNotFoundException("Product is not found for this id :: " + id);
        }
    }

    // Add Product
    // Request mapping that post the product detail in the database
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody Product product) throws ProductAlreadyExistException {
        List<Product> products = productService.findByName(product.getName());
        if (!products.isEmpty()) {
            throw new ProductAlreadyExistException("Product name already exists :: " + product.getName());
        } else {
            productService.addProduct(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

    // Update Product
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateProduct(@RequestBody Product product, @PathVariable Long id) {
        productService.updateProduct(product, id);
    }

    // Delete Product
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // Get Products by name in query string e.g. /api/v1/products/filter?name=shirt
    @CrossOrigin(origins = "*")
    @RequestMapping("/filter")
    public List<Product> findByName(@RequestParam(value = "name") String name) {
        return productService.findByName(name);
    }

    // Get Products those are most viewed
    @RequestMapping(value = "/popular/{maxTopViewLimit}", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getMostViewProducts(@PathVariable int maxTopViewLimit) {
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<List<Product>>(productService.getMostViewedProducts(maxTopViewLimit), responseHeaders,
                HttpStatus.OK);
    }
}
