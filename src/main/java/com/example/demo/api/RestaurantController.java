package com.example.demo.api;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.Restaurant;
import com.example.demo.domain.repository.RestaurantRepository;
import com.example.demo.domain.service.RestaurantService;

@RequestMapping("restaurant")
@RestController
public class RestaurantController {

  @Autowired
  private RestaurantRepository restaurantRepository;
  @Autowired
  private RestaurantService restaurantService;

  @GetMapping
  public List<Restaurant> getAll() {
    return restaurantRepository.findAll();
  }

  @GetMapping("{id}")
  public Restaurant getById(@PathVariable Long id) {
    return restaurantService.searchOrFail(id);
  }

  @GetMapping("/getByCuisine/{cuisineId}")
  public List<Restaurant> getByCozinha(@PathVariable Long cuisineId) {
    return restaurantRepository.findByCuisineId(cuisineId);
  }

  @GetMapping("/getByFreteRange")
  public List<Restaurant> getByFreteRange(BigDecimal initialTax, BigDecimal finalTax) {
    return restaurantRepository.findByFreightRateBetween(initialTax, finalTax);
  }

  @GetMapping("/search/freight/name")
  public List<Restaurant> getByFreteName(String name, BigDecimal initialTax, BigDecimal finalTax) {
    return restaurantRepository.find(name, initialTax, finalTax);
  }

  @GetMapping("/criteria-query/frete/name")
  public List<Restaurant> getWithCriteria(String name, BigDecimal initialTax, BigDecimal finalTax) {
    return restaurantRepository.findWithCriteriaQuery(name, initialTax, finalTax);
  }

  @GetMapping("/search/frete-gratis")
  public List<Restaurant> getFreeFrete(String name) {
    return restaurantRepository.freeFreight(name);
  }

  // CREATE

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Restaurant create(@RequestBody Restaurant restaurant) {
    return restaurant = restaurantService.save(restaurant);
  }

  @PutMapping("{id}")
  public Restaurant update(@RequestBody Restaurant restaurant, @PathVariable Long id) {

    Restaurant actualRestaurant = restaurantService.searchOrFail(id);

    // professor colocou pra ignorar paymentForms pra ela não ser apagada quando não
    // for passada na alteração.
    BeanUtils.copyProperties(restaurant, actualRestaurant, "id", "paymentForms");

    return restaurantService.save(actualRestaurant);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    restaurantService.remove(id);
  }
}
