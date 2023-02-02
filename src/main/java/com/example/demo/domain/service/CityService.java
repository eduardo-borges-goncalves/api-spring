package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Exceptions.EntidadeEmUsoException;
import com.example.demo.domain.Exceptions.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.City;
import com.example.demo.domain.repository.CityRepository;

@Service
public class CityService {

  private static final String NOT_FOUND_MSG = "Não existe um cadastro de cidade com o código %d.";

  @Autowired
  CityRepository cityRepository;

  public City save(City city) {
    return cityRepository.save(city);
  }

  public void remove(Long id) {
    try {
      cityRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(String.format(NOT_FOUND_MSG, id));
      
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
          String.format("Cidade de código %d não pode ser removida, pois está em uso", id));
    }
  }

  public City searchOrFail(Long id) {
    return cityRepository.findById(id)
        .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(NOT_FOUND_MSG, id)));
  }
}
