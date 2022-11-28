package com.example.csvcounter.visit;

import java.util.Objects;

public class PhoneAndMailHasher implements VisitHasher {

  @Override
  public boolean areEqual(Visit visit, Visit otherVisit) {
    return (null != visit.getPhoneNumber() && null != otherVisit.getPhoneNumber())
        && (null != visit.getEmail() && null != otherVisit.getEmail())
        && visit.getEmail().contentEquals(otherVisit.getEmail())
        && visit.getPhoneNumber().contentEquals(otherVisit.getPhoneNumber());
  }

  @Override
  public int hash(Visit visit) {
    return Objects.hash(visit.getEmail(), visit.getPhoneNumber());
  }
}
