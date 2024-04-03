package com.example.java11.entity;


import com.example.java11.reqmodels.ReqStudent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    private Group group;

    static public Student mapStudent(ReqStudent reqStudent, Group group){
        Student student = new Student();
        student.setLastName(reqStudent.getLastName());
        student.setFirstName(reqStudent.getFirstName());
        student.setMiddleName(reqStudent.getMiddleName());
        student.setGroup(group);
        return student;
    }
}
