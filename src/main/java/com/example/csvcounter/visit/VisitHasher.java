package com.example.csvcounter.visit;

public interface VisitHasher {

  boolean areEqual(Visit visit, Visit otherVisit);

  int hash(Visit visit);
}
