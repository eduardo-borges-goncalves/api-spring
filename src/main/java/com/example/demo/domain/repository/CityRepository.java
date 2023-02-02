package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
