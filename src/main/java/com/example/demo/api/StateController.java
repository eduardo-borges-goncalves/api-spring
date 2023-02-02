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
import com.example.demo.domain.model.State;
import com.example.demo.domain.repository.StateRepository;
import com.example.demo.domain.service.StateService;

@RequestMapping("state")
@RestController
public class StateController {

  @Autowired
  private StateRepository stateRepository;
  @Autowired
  private StateService stateService;

  @GetMapping
  public List<State> getAll() {
    return stateRepository.findAll();
  }

  @GetMapping("{id}")
  public ResponseEntity<State> getById(@PathVariable Long id) {
    Optional<State> state = stateRepository.findById(id);
    if (state.isEmpty())
      return ResponseEntity.notFound().build();

    return ResponseEntity.ok(state.get());
  }

  // CREATE

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseEntity<?> create(@RequestBody State state) {
    try {
      state = stateService.save(state);
      return ResponseEntity.status(HttpStatus.CREATED).body(state);
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("{id}")
  public ResponseEntity<?> update(@RequestBody State state, @PathVariable Long id) {
    Optional<State> actualPayment = stateRepository.findById(id);

    if (actualPayment.isEmpty())
      return ResponseEntity.notFound().build();

    BeanUtils.copyProperties(state, actualPayment.get(), "id");

    try {
      stateService.save(actualPayment.get());
      return ResponseEntity.status(HttpStatus.CREATED).body(actualPayment.get());
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

  @DeleteMapping("{id}")
  public ResponseEntity<State> delete(@PathVariable Long id) {
    try {
      stateService.remove(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}
