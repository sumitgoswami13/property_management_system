package PropertyManagemet.DAO;

import PropertyManagemet.model.User;
import java.util.List;

public interface UserDaoInterface {
    // Create operation
    void addUser(User user);

    // Read operation
    User getUserById(int userId);

    // Read operation to get all users
    List<User> getAllUsers();

    // Update operation
    void updateUser(User user);

    // Delete operation
    void deleteUser(int userId);
}
