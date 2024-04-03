package com.example.java11;


import com.example.java11.entity.Group;
import com.example.java11.entity.Student;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Group> groupCriteriaQuery = builder.createQuery(Group.class);
        Root<Group> root = groupCriteriaQuery.from(Group.class);
        groupCriteriaQuery.select(root);
        groupCriteriaQuery.where(builder.equal(root.get("groupName"), name));
        Query<Group> query = session.createQuery(groupCriteriaQuery);
        return query.getSingleResultOrNull();
    }

    public Group getGroupById(Long id){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Group> groupCriteriaQuery = builder.createQuery(Group.class);
        Root<Group> root = groupCriteriaQuery.from(Group.class);
        groupCriteriaQuery.select(root);
        groupCriteriaQuery.where(builder.equal(root.get("id"), id));
        Query<Group> query = session.createQuery(groupCriteriaQuery);
        return query.getSingleResultOrNull();
    }

    public void deleteGroupById(Long id){
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaDelete<Group> criteriaDelete = builder.createCriteriaDelete(Group.class);
        Root<Group> root = criteriaDelete.from(Group.class);
        criteriaDelete.where(builder.equal(root.get("id"), id));

        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();
    }

    public void deleteGroupByName(String name){
        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaDelete<Group> criteriaDelete = builder.createCriteriaDelete(Group.class);
        Root<Group> root = criteriaDelete.from(Group.class);
        criteriaDelete.where(builder.equal(root.get("groupName"), name));

        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();
    }

    public List<Group> getAllGroups(){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Group> groupCriteriaQuery = builder.createQuery(Group.class);
        Root<Group> root = groupCriteriaQuery.from(Group.class);
        groupCriteriaQuery.select(root);
        Query<Group> query = session.createQuery(groupCriteriaQuery);
        return query.getResultList();
    }
}

