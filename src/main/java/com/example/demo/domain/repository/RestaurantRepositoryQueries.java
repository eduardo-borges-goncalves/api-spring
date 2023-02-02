package com.example.demo.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.example.demo.domain.model.Restaurant;

public interface RestaurantRepositoryQueries {
  List<Restaurant> find(String name, BigDecimal initialTax, BigDecimal finalTax);

  List<Restaurant> findWithCriteriaQuery(String name, BigDecimal initialTax, BigDecimal finalTax);

  List<Restaurant> freeFreight(String name);
}