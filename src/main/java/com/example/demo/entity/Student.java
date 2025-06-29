package com.example.demo.entity;

import com.example.demo.enums.Grade;

import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Student {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  
  @NotBlank(message = "Name is required")
  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Grade grade;

  @NotNull(message = "Birth date is required")
  // @Column(nullable = false) // @NotNull adds null constraints in DDL
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date birthDate;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime dateCreated;

  @LastModifiedDate
  private LocalDateTime dateModified;

  // 
  public Student() {};

  // Updating entity
  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Grade getGrade() {
    return this.grade;
  }

  public void setGrade(Grade grade) {
    this.grade = grade;
  }

  public Date getBirthDate() {
    return this.birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getUrl() {
    return "/student/" + this.id;
  }
}