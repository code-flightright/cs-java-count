package com.example.csvcounter;

import lombok.Value;

@Value
public class VisitsResult {

  VisitSummary visits;
  
  @Value
  static class VisitSummary {

    long total;
  }
}
