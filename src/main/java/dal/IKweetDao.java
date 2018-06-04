package dal;

import domain.Kweet;
import java.util.List;
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
    List<Kweet> getKweetsByBody(String body);
    //Set<Kweet> getKweets(Long userId);
}
