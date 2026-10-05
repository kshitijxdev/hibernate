package com.hibernate;

import com.hibernate.model.Student;
import com.hibernate.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @GetMapping
    public ResponseEntity<Student> getStudentById(@RequestParam Long id){
        Student student = studentService.getStudent(id);

        return ResponseEntity.ok().body(student);
    }

    @PutMapping
    public ResponseEntity<String> updateStudent(@RequestBody Student student, @RequestParam Long id){
        studentService.updateStudent(student,id);

        return ResponseEntity.ok("Done");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent(@RequestParam Long id){
        studentService.deleteStudent(id);

        return ResponseEntity.ok("DONE");
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student){
        studentService.createStudent(student);

        return ResponseEntity.ok("Done");
    }

}
