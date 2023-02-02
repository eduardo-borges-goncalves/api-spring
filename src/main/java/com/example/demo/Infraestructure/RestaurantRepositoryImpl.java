package com.example.demo.Infraestructure;

import static com.example.demo.Infraestructure.spec.RestaurantSpecs.comFreteGratis;
import static com.example.demo.Infraestructure.spec.RestaurantSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.model.Restaurant;
import com.example.demo.domain.repository.RestaurantRepository;
import com.example.demo.domain.repository.RestaurantRepositoryQueries;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

  @PersistenceContext
  private EntityManager manager;

  @Autowired
  // this anottations serves to prevent circular dependency. It's very important.
  // That say to spring calm down, initialize this later. That allows us to use
  // The interface in their implementation
  @Lazy
  RestaurantRepository restaurantRepository;

  @Override
  public List<Restaurant> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
    var jpql = new StringBuilder();
    jpql.append("from Restaurant where 0 = 0 ");

    var parametros = new HashMap<String, Object>();

    if (nome != null && nome.length() > 0) {
      jpql.append("and nome like :nome ");
      parametros.put("nome", "%" + nome + "%");
    }

    if (taxaInicial != null) {
      jpql.append("and taxaFrete >= :taxaInicial ");
      parametros.put("taxaInicial", taxaInicial);
    }

    if (taxaFinal != null) {
      jpql.append("and taxaFrete <= :taxaFinal ");
      parametros.put("taxaFinal", taxaFinal);
    }

    var query = manager.createQuery(jpql.toString(), Restaurant.class);

    parametros.forEach((key, value) -> query.setParameter(key, value));

    return query.getResultList();
  }

  /**
   * @param nome
   * @return
   */
  public List<Restaurant> findWithCriteriaQuery(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
    CriteriaBuilder builder = manager.getCriteriaBuilder();
    CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);

    var root = criteria.from(Restaurant.class);

    var predicates = new ArrayList<Predicate>();

    if (nome != null && nome.length() > 0) {
      predicates.add(builder.like(root.get("name"), "%" + nome + "%"));
    }

    if (taxaInicial != null) {
      predicates.add(builder.greaterThanOrEqualTo(root.get("freightTax"), taxaInicial));
    }

    if (taxaFinal != null) {
      predicates.add(builder.lessThanOrEqualTo(root.get("freightTax"), taxaFinal));
    }

    criteria.where(predicates.toArray(new Predicate[0]));

    var query = manager.createQuery(criteria);
    return query.getResultList();
  }

  public List<Restaurant> freeFreight(String name) {
    return restaurantRepository.findAll(comFreteGratis().and(comNomeSemelhante(name)));
  }
}
