package com.example.demo.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.model.Restaurant;

public interface RestaurantRepository
    extends JpaRepository<Restaurant, Long>, RestaurantRepositoryQueries, JpaSpecificationExecutor<Restaurant> {

  // essa query jbdc aqui alguma coisa serve para otimizar a performance no banco
  // de dados. Ela resolve o problema do N+1 ocasionado pelo JPA. Porém, até o
  // momento, ela só retornou os restaurantes que de fato tinham payment form, o
  // que não foi legal. Resolvi adicionando a palavra left à expressão join fetch,
  // olha que coisa kkkkk
  @Query("from Restaurant r join fetch r.cuisine left join fetch r.paymentForms")
  List<Restaurant> findAll();

  public List<Restaurant> findByFreightRateBetween(BigDecimal initialTax, BigDecimal finalTax);

  public List<Restaurant> findByCuisineId(Long cuisineId);

  public List<Restaurant> findByNameContainingAndCuisineId(String name, Long cuisineId);

  public Restaurant findFirstByNameContaining(String name);

  public Restaurant findTop2ByNameContaining(String name);

  boolean existsByName(String name);

  int countByCuisineId(Long cuisineId);

}
