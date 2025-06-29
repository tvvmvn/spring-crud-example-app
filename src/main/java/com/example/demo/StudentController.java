package com.example.demo;

import com.example.demo.enums.Grade;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.entity.Student;

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

  // Save a student
  @PostMapping("/students")
  public String save(
    @Valid Student student, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      // model.addAttribute("student", student); // no need
      model.addAttribute("grade", Grade.values());

      return "student_form";
    }

    repository.save(student);

    return "redirect:/";
  }

  // Create a student
  @GetMapping("/student/create")
  public String create(Model model) {

    model.addAttribute("student", new Student());
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

  // Delete a student
  @GetMapping("/student/{id}/delete")
  public String delete(@PathVariable("id") Long id) {

    Student student = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException());

    repository.deleteById(id);

    return "redirect:/?deleted=" + student.getName();
  }
}
