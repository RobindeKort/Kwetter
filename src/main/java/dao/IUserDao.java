package dao;

import domain.User;
import java.util.Set;

/**
 *
 * @author Robin
 */
public interface IUserDao {
    User getUser(Long id);
    User getUserByUserName(String name);
    Set<User> getFollowers(Long id);
    boolean createUser(User user);
    boolean updateUser(User user);
    boolean removeUser(Long id);
}
