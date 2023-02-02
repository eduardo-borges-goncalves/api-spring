package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data // abstrai getter e setter do lombok
public class Restaurant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private BigDecimal freightRate;

  private Boolean active;
  private Boolean isOpen;

  // this annotation allows us rename the property in Json response
  @JsonProperty("relationaded cuisine") 
  @JoinColumn(nullable = false)
  @ManyToOne
  private Cuisine cuisine;

  @Embedded
  @JsonIgnore
  private Address address;
  
  // @JsonIgnore
  @ManyToMany
  @JoinTable(name = "restaurant_payment_form", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "payment_form_id"))
  private List<PaymentForm> paymentForms = new ArrayList<>();

  @JsonIgnore // previne a serialização em círculo
  @OneToMany(mappedBy = "restaurant")
  private List<Product> products = new ArrayList<>();
  
  @CreationTimestamp
  private LocalTime createDateTime;
  
  @UpdateTimestamp
  private LocalTime updateDateTime;
}
