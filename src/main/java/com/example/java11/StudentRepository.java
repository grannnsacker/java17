package com.example.java11;


import com.example.java11.entity.Student;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentRepository {
    private final SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
    }


    public void addStudent(Student student){
        session.beginTransaction();
        session.persist(student);
        session.flush();
        session.getTransaction().commit();
    }

    public Student getStudentByFirstName(String name){
        return session.createQuery("select s from Student s where s.first_name = %s".formatted(name), Student.class).getSingleResult();
    }

    public void deleteStudentByFirstName(String name){
        session.createQuery("delete from Student s where s.first_name = %s".formatted(name), Student.class).getSingleResult();
    }

    public List<Student> getAllStudents(){
        return session.createQuery("select s from Student s", Student.class).getResultList();
    }

}
