package pp.javapp312.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pp.javapp312.model.User;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{


    private final EntityManagerFactory factory;

    @Autowired
    public UserDAOImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> getAllUsers() {

        return factory.createEntityManager().createQuery("from User", User.class).getResultList();

    }

    @Override
    public void saveUser(User user) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.merge(user);
        transaction.commit();
    }

    @Override
    public User getUser(int id) {
        return factory.createEntityManager().find(User.class, id);
    }

    @Override
    public void deleteUser(int id) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        Query query = manager.createQuery("delete from User where id = :userId");
        query.setParameter("userId", id);
        query.executeUpdate();
        transaction.commit();
    }

}
