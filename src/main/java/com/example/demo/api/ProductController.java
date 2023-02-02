package com.example.demo.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Exceptions.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.repository.ProductRepository;
import com.example.demo.domain.service.ProductService;

@RequestMapping("product")
@RestController
public class ProductController {

  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private ProductService productService;

  @GetMapping
  public List<Product> getAll() {
    return productRepository.findAll();
  }

  @GetMapping("{id}")
  public ResponseEntity<Product> getById(@PathVariable Long id) {
    Optional<Product> product = productRepository.findById(id);
    if (product.isEmpty())
      return ResponseEntity.notFound().build();

    return ResponseEntity.ok(product.get());
  }

  // CREATE

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseEntity<?> create(@RequestBody Product product) {
    try {
      product = productService.save(product);
      return ResponseEntity.status(HttpStatus.CREATED).body(product);
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("{id}")
  public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Long id) {
    Optional<Product> actualPayment = productRepository.findById(id);

    if (actualPayment.isEmpty())
      return ResponseEntity.notFound().build();

    BeanUtils.copyProperties(product, actualPayment.get(), "id");

    try {
      productService.save(actualPayment.get());
      return ResponseEntity.status(HttpStatus.CREATED).body(actualPayment.get());
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

  @DeleteMapping("{id}")
  public ResponseEntity<Product> delete(@PathVariable Long id) {
    try {
      productService.remove(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}
