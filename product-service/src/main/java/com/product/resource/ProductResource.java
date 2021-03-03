package com.product.resource;

import com.product.dto.ProductDto;
import com.product.model.Product;
import com.product.service.Currency;
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
import java.util.Optional;

/**
 * REST endpoint
 *
 * @author aman
 */

@RestController
@RequestMapping("/products")
public class ProductResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);
    private int maxTopViewLimit = 5;

    @Autowired
    private ProductService productService;

    @RequestMapping("/hello")
    public String getName() {
        String returnValue = "Hello";
        LOGGER.info("{} ", returnValue);
        return returnValue;
    }

    // Get All Products
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProducts() {
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<List<Product>>(productService.getAllProducts(), responseHeaders, HttpStatus.OK);
    }

    // Get Single Product
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id,
                                                 @RequestParam("optionalCurrencyCode")
                                                         Optional<String> optionalCurrencyCode) {
        Product product = productService.getProduct(id);
        ProductDto productDto = new  ProductDto();
        System.out.print("Product" + product);
        if (product != null) {
            Currency currency = optionalCurrencyCode.isPresent()?
                    Currency.valueOf(optionalCurrencyCode.get().replaceAll("^\"|\"$", "")):Currency.USD;
            productDto = ProductMapper.productEntityToProductDto(product, currency);
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productDto, HttpStatus.NOT_FOUND);
        }
    }

    // Get Most Viewed Products
    @RequestMapping(value = "/mostviewed/{maxTopViewLimit}", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getMostViewProducts(@PathVariable int maxTopViewLimit){
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<List<Product>>(
                productService.getMostViewedProducts(maxTopViewLimit), responseHeaders, HttpStatus.OK);
    }

    // Add Product
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
        //addProduct();
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

    // Get Products by name
    @CrossOrigin(origins = "*")
    @RequestMapping("/filter")
    public List<Product> findByName(@RequestParam(value = "name") String name) {
        return productService.findByName(name);
    }
}
