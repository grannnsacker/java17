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

    @GetMapping("/get_student_by_id")
    @ResponseBody
    public Student getStudentById(@RequestParam Long id){
        return studentRepository.getStudentById(id);
    }


    @GetMapping("/get_student_by_first_name")
    @ResponseBody
    public Student getStudentByFirstName(@RequestParam String name){
        return studentRepository.getStudentByName(name, StudentRepository.NameCode.FirstName);
    }

    @GetMapping("/get_student_by_middle_name")
    @ResponseBody
    public Student getStudentByMiddleName(@RequestParam String name){
        return studentRepository.getStudentByName(name, StudentRepository.NameCode.MiddleName);
    }

    @GetMapping("/get_student_by_last_name")
    @ResponseBody
    public Student getStudentByLastName(@RequestParam String name){
        return studentRepository.getStudentByName(name, StudentRepository.NameCode.LastName);
    }

    @GetMapping("/delete_student_by_id")
    @ResponseBody
    public String deleteStudentByFirstName(@RequestParam Long id){
        studentRepository.deleteStudentById(id);
        return "Success";
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
