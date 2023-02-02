package com.example.demo.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name; 

  private String description;

  private BigDecimal price; 

  private Boolean isActive;

  // @JsonIgnore
  @ManyToOne
  @JoinColumn(nullable = false)
  private Restaurant restaurant;
}
