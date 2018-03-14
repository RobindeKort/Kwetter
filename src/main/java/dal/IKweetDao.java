package dal;

import domain.Kweet;
import java.util.Set;

/**
 *
 * @author Robin
 */
public interface IKweetDao {
    void createKweet(Kweet kweet);
    void updateKweet(Kweet kweet);
    void removeKweet(Kweet kweet);
    Kweet getKweet(Long id);
    //Set<Kweet> getKweets(Long userId);
}
