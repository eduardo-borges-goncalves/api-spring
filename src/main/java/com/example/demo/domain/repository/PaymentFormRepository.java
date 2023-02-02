package com.example.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.model.PaymentForm;

public interface PaymentFormRepository extends JpaRepository<PaymentForm, Long> {

}
