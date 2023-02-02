package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Exceptions.EntidadeEmUsoException;
import com.example.demo.domain.Exceptions.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.State;
import com.example.demo.domain.repository.StateRepository;

@Service
public class StateService {
  private static final String NOT_FOUND_MSG = "Não existe um cadastro de estado com o código %d.";

  @Autowired
  StateRepository stateRepository;

  public State save(State state) {
    return stateRepository.save(state);
  }

  public void remove(Long id) {
    try {
      stateRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(String.format(NOT_FOUND_MSG, id));

    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
          String.format("Estado de código %d não pode ser removido, pois está em uso", id));
    }
  }

  public State searchOrFail(Long id) {
    return stateRepository.findById(id)
        .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(NOT_FOUND_MSG, id)));
  }
}
