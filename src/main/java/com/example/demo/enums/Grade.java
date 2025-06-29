package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Grade {
  
  FIRST_YEAR("1st"),
  SECOND_YEAR("2rd"),
  THIRD_YEAR("3rd");

  private final String label;

  Grade(String label) {
    this.label = label;
  }

  @JsonValue
  public String getLabel() {
    return this.label;
  }
}
