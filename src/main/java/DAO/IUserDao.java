package DAO;

import Domain.User;
import java.util.Set;

/**
 *
 * @author Robin
 */
public interface IUserDao {
    User getUser(int id);
    Set<User> getUserByUserName(String name);
    Set<User> getFollowers(int id);
    User addUser(User user);
    boolean removeUser(int id);
}
