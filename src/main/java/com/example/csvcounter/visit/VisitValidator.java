package com.example.csvcounter.visit;

import static org.springframework.util.StringUtils.hasText;

import lombok.experimental.UtilityClass;

@UtilityClass
public class VisitValidator {

  public static boolean hasNoEmptyFields(Visit visit) {
    return (hasText(
        visit.getEmail())
        && hasText(visit.getPhoneNumber())
        && hasText(visit.getSource()));
  }
}
