package com.example.java11;


import com.example.java11.entity.Group;
import com.example.java11.entity.Student;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GroupRepository {


    private final SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
    }


    public void addGroup(Group group){
        session.beginTransaction();
        session.persist(group);
        session.flush();
        session.getTransaction().commit();
    }

    public Group getGroupByName(String name){
        return session.createQuery("select s from Group s where s.groupName = %s".formatted(name), Group.class).getSingleResult();
    }

    public void deleteGroupByName(String name){
        session.createQuery("delete from Group s where s.groupName = %s".formatted(name), Group.class).getSingleResult();
    }

    public List<Group> getAllGroups(){
        return session.createQuery("select s from Group s", Group.class).getResultList();
    }
}

