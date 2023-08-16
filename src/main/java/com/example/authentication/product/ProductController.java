package com.example.authentication.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable(value = "id") UUID id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable(value = "id") UUID id, @RequestBody Product product) {
        return productService.findById(id)
                .map(prod -> {
                    product.setId(prod.getId());
                    return ResponseEntity.ok(productService.save(product));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") UUID id) {
        return productService.findById(id)
                .map(prod -> {
                    productService.delete(prod);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
