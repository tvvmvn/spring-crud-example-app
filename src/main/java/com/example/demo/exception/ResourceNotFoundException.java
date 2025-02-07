package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such resource")
public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException() {}
}
