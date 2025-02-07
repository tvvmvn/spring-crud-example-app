package com.example.demo;

import com.example.demo.enums.Grade;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Student {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
  
  @NotEmpty(message = "Name is required")
  private String name;

  @NotNull(message = "Grade is required")
  private Grade grade;

  @NotNull(message = "Birth date is required")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthDate;

  // Initializer
  public Student() {};

  // Getter and setter
  public long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBirthDate() {
    return this.birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public Grade getGrade() {
    return this.grade;
  }

  public void setGrade(Grade grade) {
    this.grade = grade;
  }

  public String getUrl() {
    return "/student/" + this.id;
  }
}