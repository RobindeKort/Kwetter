package domain;

import domain.User;
import domain.Kweet;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robin
 */
public class UserServiceTest {
    final int userCount = 10;
    Queue<User> users;
    
    @Before
    public void setUp() {
        users = new PriorityQueue();
        for (int i = 0; i < userCount; i++) {
            users.add(new User(String.format("User%02d", i)));
        }
    }
    
    /**
     * Test user.setKweets(Set<Kweet>) and user.getKweets()
     */
    @Test
    public void testGetSetKweets() {
        User u = users.peek();
        Kweet kweet1 = new Kweet("test kweet 1");
        Kweet kweet2 = new Kweet("test kweet 2");
        u.addKweet(kweet1);
        u.addKweet(kweet2);
        
        assertTrue(u.getKweets().contains(kweet1));
        assertTrue(u.getKweets().contains(kweet2));
    }
    
    /**
     * Test user.follow(User), user.getFollowing() and user.getFollowedBy()
     */
    @Test
    public void testFollow() {
        User u1 = users.poll();
        User u2 = users.poll();
        assertFalse(u1.getFollowing().contains(u2));
        assertFalse(u2.getFollowedBy().contains(u1));
        u1.follow(u2);
        assertTrue(u1.getFollowing().contains(u2));
        assertTrue(u2.getFollowedBy().contains(u1));
    }
    
    /**
     * Test user.unfollow(User)
     */
    @Test
    public void testUnfollow() {
        User u1 = users.poll();
        User u2 = users.poll();
        u1.follow(u2);
        assertTrue(u1.getFollowing().contains(u2));
        assertTrue(u2.getFollowedBy().contains(u1));
        u1.unfollow(u2);
        assertFalse(u1.getFollowing().contains(u2));
        assertFalse(u2.getFollowedBy().contains(u1));
        u1.unfollow(u2);
        assertFalse(u1.getFollowing().contains(u2));
        assertFalse(u2.getFollowedBy().contains(u1));
    }
    
    /*
    * Test every test-user sending 10 kweets
    */
    @Test
    public void testKweet() {
        final int kweetCount = 10;
        for (User user : users) {
            for (int i = 0; i < kweetCount; i++) {
                user.addKweet(new Kweet(String.format("Kweet%02d", i)));
            }
            assertEquals(user.getKweetCount(), kweetCount);
        }
    }
}
