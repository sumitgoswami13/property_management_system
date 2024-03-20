package PropertyManagemet.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import PropertyManagemet.model.User;

import java.util.List;

public class UserDao implements UserDaoInterface {
    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Create operation
    @Override
    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.print(ex);
            throw new RuntimeException("Failed to add user: " + ex.getMessage());
        }
    }

    // Read operation
    @Override
    public User getUserById(int userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, userId);
        } catch (HibernateException ex) {
            System.out.print(ex);
            throw new RuntimeException("Failed to get user by ID: " + ex.getMessage());
        }
    }
    
    // Read operation to get all users
    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).getResultList();
        } catch (HibernateException ex) {
            System.out.print(ex);
            throw new RuntimeException("Failed to retrieve all users: " + ex.getMessage());
        }
    }

    // Update operation
    @Override
    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.print(ex);
            throw new RuntimeException("Failed to update user: " + ex.getMessage());
        }
    }

    // Delete operation
    @Override
    public void deleteUser(int userId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                session.delete(user);
                transaction.commit();
            }
        } catch (HibernateException ex) {
            System.out.print(ex);
            throw new RuntimeException("Failed to delete user: " + ex.getMessage());
        }
    }
}
