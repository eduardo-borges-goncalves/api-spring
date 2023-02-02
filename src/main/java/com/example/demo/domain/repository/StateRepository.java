package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.model.State;

public interface StateRepository extends JpaRepository<State, Long> {

}
