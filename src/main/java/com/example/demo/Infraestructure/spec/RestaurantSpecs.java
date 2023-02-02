package com.example.demo.Infraestructure.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.domain.model.Restaurant;

public class RestaurantSpecs {
  public static Specification<Restaurant> comFreteGratis() {
    return (root, query, builder) -> builder.equal(root.get("freightRate"), BigDecimal.ZERO);
  }

  public static Specification<Restaurant> comNomeSemelhante(String nome) {
    return (root, query, builder) -> builder.like(root.get("name"), "%" + nome + "%");
  }
}
