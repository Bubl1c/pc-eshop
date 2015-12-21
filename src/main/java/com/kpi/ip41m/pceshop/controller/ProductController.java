package com.kpi.ip41m.pceshop.controller;

import com.kpi.ip41m.pceshop.entity.product.Product;
import com.kpi.ip41m.pceshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andrii on 16.12.2015.
 */
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Product> get() {
        return productRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long save(@RequestBody Product product) {
        Product saved = productRepository.save(product);
        return saved.getId();
    }

    @RequestMapping(value = "/{id}/remove", method = RequestMethod.POST)
    public void remove(@PathVariable("id") Long id) {
        productRepository.delete(id);
    }
}
