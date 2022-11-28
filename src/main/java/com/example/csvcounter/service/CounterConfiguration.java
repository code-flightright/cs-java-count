package com.example.csvcounter.service;

import com.example.csvcounter.visit.PhoneAndMailHasher;
import com.example.csvcounter.visit.Visit;
import com.example.csvcounter.visit.VisitHasher;
import com.example.csvcounter.visit.VisitValidator;
import java.util.function.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterConfiguration {

  @Bean
  public Predicate<Visit> acceptanceCriteria() {
    return VisitValidator::hasNoEmptyFields;
  }

  @Bean
  public VisitHasher visitHasher() {
    return new PhoneAndMailHasher();
  }

}
