package DAO;

import Domain.*;
import java.util.Set;

/**
 *
 * @author Robin
 */
public interface IKweetDao {
    Kweet getKweet(int id);
    Set<Kweet> getKweets(int userId);
    Set<Kweet> getFollowingKweets(int userId);
    boolean removeKweet(int id);
}
