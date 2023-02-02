package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Exceptions.EntidadeEmUsoException;
import com.example.demo.domain.Exceptions.EntidadeNaoEncontradaException;
import com.example.demo.domain.model.PaymentForm;
import com.example.demo.domain.repository.PaymentFormRepository;

@Service
public class PaymentFormService {

  private static final String NOT_FOUND_MSG = "Não existe cadastro de uma forma de pagamento com o código %d.";

  @Autowired
  PaymentFormRepository paymentFormRepository;

  public PaymentForm save(PaymentForm paymentForm) {
    return paymentFormRepository.save(paymentForm);
  }

  public void remove(Long id) {
    try {
      paymentFormRepository.deleteById(id);
      
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(String.format(NOT_FOUND_MSG, id));

    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(
          String.format("Forma de pagamento de código %d não pode ser removida, pois está em uso", id));
    }
  }

  public PaymentForm searchOrFail(Long id) {
    return paymentFormRepository.findById(id)
        .orElseThrow(() -> new EntidadeNaoEncontradaException(
            String.format(NOT_FOUND_MSG, id)));
  }
}
