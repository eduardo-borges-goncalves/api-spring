package com.example.demo.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table // serve para nomear ou mapear entidades existentes
@Getter // automatiza o processo de get and set do java por meio da lib lombok
@Setter
public class Cuisine {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @JsonIgnore // previne a serialização em círculo
  @OneToMany(mappedBy = "cuisine")
  private List<Restaurant> restaurants = new ArrayList<>();
}
