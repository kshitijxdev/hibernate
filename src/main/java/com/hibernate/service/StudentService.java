package com.hibernate.service;

import com.hibernate.model.Student;
import com.hibernate.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    @Transactional
    public void createStudent(Student student){
        studentRepository.save(student);
    }

    @Transactional
    public Student getStudent(Long id){
        return studentRepository.finById(id);
    }

    @Transactional
    public void deleteStudent(Long id){
        Student student = studentRepository.finById(id);

        if(student==null){
        throw new RuntimeException("Student Not Found");
        }

        studentRepository.remove(student);
    }

    @Transactional
    public void updateStudent(Student studentReq, Long id){
        Student student1 = studentRepository.finById(id);

        student1.setName(studentReq.getName());
        student1.setAge(studentReq.getAge());
        student1.setEmail(studentReq.getEmail());

    }

}
