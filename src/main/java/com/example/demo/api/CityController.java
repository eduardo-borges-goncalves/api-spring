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
import com.example.demo.domain.model.City;
import com.example.demo.domain.repository.CityRepository;
import com.example.demo.domain.service.CityService;

@RequestMapping("city")
@RestController
public class CityController {

  @Autowired
  private CityRepository cityRepository;
  @Autowired
  private CityService cityService;

  @GetMapping
  public List<City> getAll() {
    return cityRepository.findAll();
  }

  @GetMapping("{id}")
  public ResponseEntity<City> getById(@PathVariable Long id) {
    Optional<City> city = cityRepository.findById(id);
    if (city.isEmpty())
      return ResponseEntity.notFound().build();

    return ResponseEntity.ok(city.get());
  }

  // CREATE

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseEntity<?> create(@RequestBody City city) {
    try {
      city = cityService.save(city);
      return ResponseEntity.status(HttpStatus.CREATED).body(city);
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("{id}")
  public ResponseEntity<?> update(@RequestBody City city, @PathVariable Long id) {
    Optional<City> actualPayment = cityRepository.findById(id);

    if (actualPayment.isEmpty())
      return ResponseEntity.notFound().build();

    BeanUtils.copyProperties(city, actualPayment.get(), "id");

    try {
      cityService.save(actualPayment.get());
      return ResponseEntity.status(HttpStatus.CREATED).body(actualPayment.get());
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

  @DeleteMapping("{id}")
  public ResponseEntity<City> delete(@PathVariable Long id) {
    try {
      cityService.remove(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}
