package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Exceptions.EntidadeEmUsoException;
import com.example.demo.domain.Exceptions.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.repository.ProductRepository;

@Service
public class ProductService {
  @Autowired
  ProductRepository productRepository;

  public Product save(Product product) {
    return productRepository.save(product);
  }

  public void remove(Long id) {
    try {
      productRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(
          String.format("Não existe um cadastro de produto com o código %d", id));
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
          String.format("Product de código %d não pode ser removida, pois está em uso", id));
    }
  }
}
