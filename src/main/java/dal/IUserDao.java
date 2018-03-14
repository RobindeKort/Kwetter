package dal;

import domain.Kweet;
import domain.User;
import java.util.Set;

/**
 *
 * @author Robin
 */
public interface IUserDao {
    void createUser(User user);
    void updateUser(User user);
    void removeUser(User user);
    User getUser(Long id);
    User getUserByUserName(String name);
    Set<User> getUsersByUserName(String partialName);
    //Set<User> getFollowers(Long id);
    //Set<Kweet> getFollowingKweets(User user, int amount);
}
