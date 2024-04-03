package com.example.java11;

import com.example.java11.entity.Student;
import com.example.java11.reqmodels.ReqStudent;
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
    }

    @GetMapping("/delete_student_by_first_name")
    @ResponseBody
    public String deleteStudentByFirstName(@RequestParam String firstName){
        studentRepository.deleteStudentByFirstName(firstName);
        return "Student with " + firstName + " was successfully deleted";
    }

    @PostMapping("/post_student")
    @ResponseBody
    public String postStudent(@RequestBody ReqStudent reqStudent){
        studentRepository.addStudent(reqStudent);
        return "Success";
    }

    @GetMapping("/get_all_student")
    @ResponseBody
    public List<Student> getAllStudent(){
        return studentRepository.getAllStudents();
    }

}
