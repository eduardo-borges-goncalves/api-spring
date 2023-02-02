package com.example.demo.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
// essa anotação serve para dizer ao java que essa classe é parte de outra
// entidade,
// ela é embutida, embeddable, não é uma tabela ou entidade por si só
// faz parte da estratégia de componentização das entidades
@Embeddable
public class Address {

  @Column(name = "address_zipCode")
  private String zipCode;

  @Column(name = "address_street")
  private String street;

  @Column(name = "address_number")
  private String number;

  @Column(name = "address_complement")
  private String complement;

  @Column(name = "address_neighborhood")
  private String neighborhood;

  // essa estratégia de LAZY só é boa quando vai se ignorar a a propriedade
  // relacionada.
  // caso resolvamos retornar a propriedade no JSON, não devemos usar ela de forma
  // nenhuma, pois traz muito mais complexidade pro código. O JPA cria uma classe
  // proxy
  // e pra retornarmos precisamos ignorar uma propriedade que ele tem chamada
  // "hibernateLazyInitializer"
  // com o @JsonIgnoreProperties. Então, para evitar erro, apenas não use.
  // Aqui ela não tá quebrando porque lá na classe restaurante nós chamamos o json
  // ignore para a prop Address, senão, quebraria.
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "adress_city_id")
  private City city;
}
