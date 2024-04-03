package com.example.java11;

import com.example.java11.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class StudentControllers {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/home")
    public String home(){
        return "index.html";
    }

    @GetMapping("/get_student_by_first_name")
    @ResponseBody
    public String getStudentByFistName(@RequestParam String name){
        return studentRepository.getStudentByFirstName(name).toString();
        // http://localhost:8080/get_student_by_first_name?name=Gosha
    }

    @GetMapping("/delete_student_by_first_name")
    @ResponseBody
    public String deleteStudentByFirstName(@RequestParam String firstName){
        studentRepository.deleteStudentByFirstName(firstName);
        return "Student with " + firstName + " was successfully deleted";
//        http://localhost:8080/delete_student_by_first_name?firstName=Gosha
    }

    @PostMapping("/post_student")
    @ResponseBody
    public String postStudent(@RequestBody Student student){
        studentRepository.addStudent(student);
        return "Success";
        //        http://localhost:8080/post_student?firstName=Gosha&lastName=Alexandridi&middleName=Dmitrievich
//        http://localhost:8080/post_student?firstName=Sasha&lastName=Petrov&middleName=Maksimovich
    }




    @GetMapping("/get_all_student")
    @ResponseBody
    public List<Student> getAllStudent(){
        return studentRepository.getAllStudents();
//        http://localhost:8080/get_all_student
    }




}
