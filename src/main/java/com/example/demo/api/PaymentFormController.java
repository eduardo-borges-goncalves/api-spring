package com.example.demo.api;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.model.PaymentForm;
import com.example.demo.domain.repository.PaymentFormRepository;
import com.example.demo.domain.service.PaymentFormService;

@RequestMapping("payment-form")
@RestController
public class PaymentFormController {

  @Autowired
  private PaymentFormRepository paymentFormRepository;
  @Autowired
  private PaymentFormService paymentFormService;

  @GetMapping
  public List<PaymentForm> getAll() {
    return paymentFormRepository.findAll();
  }

  @GetMapping("{id}")
  public PaymentForm getById(@PathVariable Long id) {
    return paymentFormService.searchOrFail(id);
  }

  // CREATE

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public PaymentForm create(@RequestBody PaymentForm paymentForm) {
    return paymentForm = paymentFormService.save(paymentForm);
  }

  @PutMapping("{id}")
  public PaymentForm update(@RequestBody PaymentForm paymentForm, @PathVariable Long id) {
    PaymentForm actualPayment = paymentFormService.searchOrFail(id);

    BeanUtils.copyProperties(paymentForm, actualPayment, "id");

    return paymentFormService.save(actualPayment);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable Long id) {
    paymentFormService.remove(id);
  }
}
