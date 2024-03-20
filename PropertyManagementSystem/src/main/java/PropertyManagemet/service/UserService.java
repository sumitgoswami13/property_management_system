package PropertyManagemet.service;

import PropertyManagemet.DAO.UserDaoInterface;
import PropertyManagemet.model.User;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserService implements UserServiceInterface {
    private final UserDaoInterface userDao;

    public UserService(UserDaoInterface userDao) {
        this.userDao = userDao;
    }

    // Create operation
    @Override
    public void addUser(User user) {
        try {
            userDao.addUser(user);
        } catch (Exception e) {
            System.out.print(e);
            // Handle exception as needed
        }
    }

    // Read operation
    @Override
    public User getUserById(int userId) {
        try {
            return userDao.getUserById(userId);
        } catch (Exception e) {
            System.out.print(e);
            // Handle exception as needed
            return null;
        }
    }
    
    // Read operation to get all users
    @Override
    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (Exception e) {
            System.out.print(e);
            // Handle exception as needed
            return null;
        }
    }

    // Update operation
    @Override
    public void updateUser(User user) {
        try {
            userDao.updateUser(user);
        } catch (Exception e) {
            System.out.print(e);
            // Handle exception as needed
        }
    }

    // Delete operation
    @Override
    public void deleteUser(int userId) {
        try {
            userDao.deleteUser(userId);
        } catch (Exception e) {
            System.out.print(e);
            // Handle exception as needed
        }
    }
}
