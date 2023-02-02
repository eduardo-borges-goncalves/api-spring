package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Exceptions.EntidadeEmUsoException;
import com.example.demo.domain.Exceptions.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.Restaurant;
import com.example.demo.domain.repository.CuisineRepository;
import com.example.demo.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {

  private static final String NOT_FOUND_MSG = "Não existe um cadastro de restaurante com o código %d.";

  @Autowired
  RestaurantRepository restaurantRepository;
  @Autowired
  CuisineRepository cuisineRepository;

  @Autowired
  CuisineService cuisineService;

  public Restaurant save(Restaurant restaurant) {
    var cuisineId = restaurant.getCuisine().getId();

    var cuisine = cuisineService.searchOrFail(cuisineId);

    restaurant.setCuisine(cuisine);
    restaurantRepository.save(restaurant);
    return restaurant;
  }

  public void remove(Long id) {
    try {
      restaurantRepository.deleteById(id);

    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(String.format(NOT_FOUND_MSG, id));

    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
          String.format("Restaurante de código %d não pode ser removido, pois está em uso", id));
    }
  }

  public Restaurant searchOrFail(Long id) {
    return restaurantRepository.findById(id)
        .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(NOT_FOUND_MSG, id)));
  }
}
