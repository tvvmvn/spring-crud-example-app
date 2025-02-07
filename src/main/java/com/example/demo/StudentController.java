package com.example.demo;

import com.example.demo.enums.Grade;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class StudentController {

  private StudentRepository repository;

  StudentController(StudentRepository repository) {
    this.repository = repository;
  }

  // Get all students
  @GetMapping("/")
  public String getAll(Model model) {
    List<Student> students = repository.findAll();

    model.addAttribute("students", students);

    return "home";
  }

  // Create a student
  @GetMapping("/student/create")
  public String greetingForm(Model model) {

    Student student = new Student();

    model.addAttribute("student", student);

    model.addAttribute("grade", Grade.values());

    return "student_form";
  }

  // Update a student
  @GetMapping("/student/{id}/update")
  public String update(@PathVariable("id") Long id, Model model) {

    Student student = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException());

    model.addAttribute("student", student);

    model.addAttribute("grade", Grade.values());

    return "student_form";
  }

  // Save a student
  @PostMapping("/student/create")
  public String greetingSubmit(@Valid Student student, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("grade", Grade.values());

      return "student_form";
    }

    repository.save(student);

    return "redirect:/";
  }

  // Delete a student
  @GetMapping("/student/{id}/delete")
  public String deleteStudent(@PathVariable("id") Long id) {

    Student student = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException());

    repository.deleteById(id);

    return "redirect:/";
  }
}
