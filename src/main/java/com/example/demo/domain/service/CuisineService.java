package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Exceptions.EntidadeEmUsoException;
import com.example.demo.domain.Exceptions.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Cuisine;
import com.example.demo.domain.repository.CuisineRepository;

@Service
public class CuisineService {

  @Autowired
  CuisineRepository cuisineRepository;

  private static final String NOT_FOUND_MSG = "Não existe um cadastro de cozinha com o código %d.";

  public Cuisine save(Cuisine cuisine) {
    return cuisineRepository.save(cuisine);
  }

  public void remove(Long id) {
    try {
      cuisineRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(
          String.format(NOT_FOUND_MSG, id));
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
          String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
    }
  }

  public Cuisine searchOrFail(Long id) {
    return cuisineRepository.findById(id)
        .orElseThrow(() -> new EntidadeNaoEncontradaException(
            String.format(NOT_FOUND_MSG, id)));
  }

}
