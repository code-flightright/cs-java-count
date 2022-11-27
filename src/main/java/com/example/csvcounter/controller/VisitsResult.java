package com.example.csvcounter.controller;

import lombok.Value;

@Value
public class VisitsResult {

  VisitSummary visits;
  
  @Value
  static class VisitSummary {

    long total;
  }
}
