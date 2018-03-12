package dao;

import domain.Kweet;
import java.util.Set;

/**
 *
 * @author Robin
 */
public interface IKweetDao {
    Kweet getKweet(Long id);
    Set<Kweet> getKweets(Long userId);
    Set<Kweet> getFollowingKweets(Long userId);
    boolean createKweet(Kweet kweet);
    //boolean updateKweet(Kweet kweet);
    boolean removeKweet(Long id);
}
