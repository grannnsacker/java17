package com.example.java11;


import com.example.java11.entity.Group;
import com.example.java11.entity.Student;
import com.example.java11.reqmodels.ReqStudent;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class StudentRepository {
    public enum NameCode {
        FirstName,
        MiddleName,
        LastName
    }
    private final SessionFactory sessionFactory;
    private Session session;


    @Autowired
    private GroupRepository groupRepository;

    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
    }

    public void addStudent(ReqStudent reqStudent){
        Group group = groupRepository.getGroupById(reqStudent.getGroupId());
        Student student = Student.mapStudent(reqStudent, group);
        session.beginTransaction();
        session.persist(student);
        session.flush();
        session.getTransaction().commit();
    }

    public Student getStudentByName(String name, NameCode nameCode){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> studentCriteriaQuery = builder.createQuery(Student.class);
        Root<Student> root = studentCriteriaQuery.from(Student.class);
        studentCriteriaQuery.select(root);
        switch (nameCode){
            case FirstName -> studentCriteriaQuery.where(builder.equal(root.get("firstName"), name));
            case MiddleName -> studentCriteriaQuery.where(builder.equal(root.get("middleName"), name));
            case LastName -> studentCriteriaQuery.where(builder.equal(root.get("lastName"), name));
        }
        Query<Student> query = session.createQuery(studentCriteriaQuery);
        return query.getSingleResultOrNull();
    }

    public Student getStudentById(Long id){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> studentCriteriaQuery = builder.createQuery(Student.class);
        Root<Student> root = studentCriteriaQuery.from(Student.class);
        studentCriteriaQuery.select(root);
        studentCriteriaQuery.where(builder.equal(root.get("id"), id));
        Query<Student> query = session.createQuery(studentCriteriaQuery);
        return query.getSingleResultOrNull();
    }


    public void deleteStudentById(Long id){
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaDelete<Student> criteriaDelete = builder.createCriteriaDelete(Student.class);
        Root<Student> root = criteriaDelete.from(Student.class);
        criteriaDelete.where(builder.equal(root.get("id"), id));

        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();


    }

    public List<Student> getAllStudents(){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> studentCriteriaQuery = builder.createQuery(Student.class);
        Root<Student> root = studentCriteriaQuery.from(Student.class);
        studentCriteriaQuery.select(root);
        Query<Student> query = session.createQuery(studentCriteriaQuery);
        return query.getResultList();
    }

}
