package com.pac.controller;

import com.pac.model.student;
import com.pac.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository repo;

    @PostMapping
    public student create(@RequestBody student student){
        return repo.save(student);
    }

    @PostMapping("/list")
    public List<student> createStudent(@RequestBody List<student> students){
        return repo.saveAll(students);
    }

    @GetMapping("/list")
    public List<student> getAll(){

        return repo.findAll();
    }

    @GetMapping("/{id}")

    public student getById(@PathVariable Long id){

        return repo.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public student update(@PathVariable Long id, @RequestBody student student) {
        student.setId(id);
        return repo.save(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }






}
