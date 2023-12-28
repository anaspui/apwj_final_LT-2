package dev.repository;

import dev.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Queue;

@Repository
public class UserRepository {

    private SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    public void edit(User user) {
        Session session = sessionFactory.getCurrentSession();
        User existingUser = (User) session.merge(user);
        existingUser.setFullname(user.getFullname());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setDateOfBirth(user.getDateOfBirth());
        session.flush();
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = get(id);
        session.delete(user);
    }

    public int countUsers() {
        List<User> users = getAll();
        return users.size();
    }
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<User> userQuery = session.createQuery("from User", User.class);
        return userQuery.getResultList();
    }

    public User get(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }
}
