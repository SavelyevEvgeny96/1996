package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private static final String CREAT_USER_TABLE = "CREATE TABLE IF NOT EXISTS human " +
            "(ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
            "name varchar(40) NOT NULL, " +
            "lastname varchar(40) NOT NULL, age TINYINT)";
    private static final String CLEAN_TABLE = "DELETE FROM human";
    private static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS human";


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(CREAT_USER_TABLE).executeUpdate();
            session.getTransaction().commit();
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(DROP_USER_TABLE).executeUpdate();
            session.getTransaction().commit();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User newUser = new User(name, lastName, age);
            session.save(newUser);
            session.getTransaction().commit();
        }


    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User newUser = session.get(User.class, id);
            if (newUser != null)
                session.delete(newUser);


            session.getTransaction().commit();
        }
    }


    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> userRoot = criteriaQuery.from(User.class);
            CriteriaQuery<User> all = criteriaQuery.select(userRoot);

            TypedQuery<User> allQuery = session.createQuery(all);
            List<User> result = allQuery.getResultList();
            session.getTransaction();

            return result;
        }


    }


    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.createSQLQuery(CLEAN_TABLE).executeUpdate();
            session.getTransaction().commit();


        }

    }
}
