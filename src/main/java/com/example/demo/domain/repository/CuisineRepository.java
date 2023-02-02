package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.model.Cuisine;

public interface CuisineRepository extends JpaRepository<Cuisine, Long>{
  public List<Cuisine> findByNameContaining(String name);
}
