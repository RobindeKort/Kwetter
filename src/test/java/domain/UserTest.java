package domain;

import java.util.Collections;
import java.util.HashSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robin
 */
public class UserTest {
    @Test
    public void testConstructorEmpty() {
        System.out.println("constructorEmpty");
        User instance = new User();
        assertEquals(instance.getId(), null);
        assertEquals(instance.getUserName(), null);
        assertEquals(instance.checkPassword(""), false);
        assertEquals(instance.checkPassword("asdf"), false);
        assertEquals(instance.getEmail(), null);
        assertEquals(instance.getPicturePath(), null);
        assertEquals(instance.getFirstName(), null);
        assertEquals(instance.getLastName(), null);
        assertEquals(instance.getBio(), null);
        assertEquals(instance.getLocation(), null);
        assertEquals(instance.getWebsite(), null);
        assertEquals(instance.getRole(), null);
        assertEquals(instance.getFollowingCount(), 0);
        assertEquals(instance.getFollowing(), Collections.unmodifiableSet(new HashSet<User>()));
        assertEquals(instance.getFollowedByCount(), 0);
        assertEquals(instance.getFollowedBy(), Collections.unmodifiableSet(new HashSet<User>()));
        assertEquals(instance.getKweetCount(), 0);
        assertEquals(instance.getKweets(), Collections.unmodifiableSet(new HashSet<Kweet>()));
    }
    
    @Test
    public void testConstructorUsername() {
        System.out.println("constructorUsername");
        User instance = new User("asdf");
        assertEquals(instance.getId(), null);
        assertEquals(instance.getUserName(), "asdf");
        assertEquals(instance.checkPassword("password"), true);
        assertEquals(instance.checkPassword("asdf"), false);
        assertEquals(instance.getEmail(), "");
        assertEquals(instance.getPicturePath(), "");
        assertEquals(instance.getFirstName(), "");
        assertEquals(instance.getLastName(), "");
        assertEquals(instance.getBio(), "");
        assertEquals(instance.getLocation(), "");
        assertEquals(instance.getWebsite(), "");
        assertEquals(instance.getRole(), Role.User);
        assertEquals(instance.getFollowingCount(), 0);
        assertEquals(instance.getFollowing(), Collections.unmodifiableSet(new HashSet<User>()));
        assertEquals(instance.getFollowedByCount(), 0);
        assertEquals(instance.getFollowedBy(), Collections.unmodifiableSet(new HashSet<User>()));
        assertEquals(instance.getKweetCount(), 0);
        assertEquals(instance.getKweets(), Collections.unmodifiableSet(new HashSet<Kweet>()));
    }
    
    @Test
    public void testConstructorFull() {
        System.out.println("constructorFull");
        User instance = new User("Username", "Password", "Email", "Picture", 
                                 "Firstname", "Lastname", "Bio", "Location", 
                                 "Website");
        assertEquals(instance.getId(), null);
        assertEquals(instance.getUserName(), "Username");
        assertEquals(instance.checkPassword("Password"), true);
        assertEquals(instance.checkPassword("password"), false);
        assertEquals(instance.getEmail(), "Email");
        assertEquals(instance.getPicturePath(), "Picture");
        assertEquals(instance.getFirstName(), "Firstname");
        assertEquals(instance.getLastName(), "Lastname");
        assertEquals(instance.getBio(), "Bio");
        assertEquals(instance.getLocation(), "Location");
        assertEquals(instance.getWebsite(), "Website");
        assertEquals(instance.getRole(), Role.User);
        assertEquals(instance.getFollowingCount(), 0);
        assertEquals(instance.getFollowing(), Collections.unmodifiableSet(new HashSet<User>()));
        assertEquals(instance.getFollowedByCount(), 0);
        assertEquals(instance.getFollowedBy(), Collections.unmodifiableSet(new HashSet<User>()));
        assertEquals(instance.getKweetCount(), 0);
        assertEquals(instance.getKweets(), Collections.unmodifiableSet(new HashSet<Kweet>()));
    }
    
    @Test
    public void testSetId() {
        System.out.println("setId");
        User instance = new User();
        instance.setId(2L);
        Long expResult = 2L;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetUserName() {
        System.out.println("setUserName");
        User instance = new User();
        instance.setUserName("asdf");
        String expResult = "asdf";
        String result = instance.getUserName();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        User instance = new User("name");
        boolean expResult = true;
        boolean result = instance.checkPassword("password");
        assertTrue(result);
        instance.setPassword("password", "asdf");
        result = instance.checkPassword("asdf");
        assertTrue(result);
        instance.setPassword("fakepass", "qwer");
        result = instance.checkPassword("asdf");
        assertTrue(result);
        result = instance.checkPassword("qwer");
        assertFalse(result);
    }
    
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        User instance = new User();
        instance.setEmail("asdf");
        String expResult = "asdf";
        String result = instance.getEmail();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetPicturePath() {
        System.out.println("setPicturePath");
        User instance = new User();
        instance.setPicturePath("asdf");
        String expResult = "asdf";
        String result = instance.getPicturePath();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        User instance = new User();
        instance.setFirstName("asdf");
        String expResult = "asdf";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        User instance = new User();
        instance.setLastName("asdf");
        String expResult = "asdf";
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetBio() {
        System.out.println("setBio");
        User instance = new User();
        instance.setBio("asdf");
        String expResult = "asdf";
        String result = instance.getBio();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetLocation() {
        System.out.println("setLocation");
        User instance = new User();
        instance.setLocation("asdf");
        String expResult = "asdf";
        String result = instance.getLocation();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetWebsite() {
        System.out.println("setWebsite");
        User instance = new User();
        instance.setWebsite("asdf");
        String expResult = "asdf";
        String result = instance.getWebsite();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetRole() {
        System.out.println("setRole");
        User instance = new User();
        instance.setRole(Role.Moderator);
        Role expResult = Role.Moderator;
        Role result = instance.getRole();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAddRemoveFollowing() {
        System.out.println("setAddRemoveFollowing");
        User instance = new User("name");
        User u2 = new User("name2");
        int expResult = 0;
        int result = instance.getFollowingCount();
        assertEquals(expResult, result);
        instance.follow(instance);
        result = instance.getFollowingCount();
        assertEquals(expResult, result);
        instance.follow(u2);
        expResult = 1;
        result = instance.getFollowingCount();
        assertEquals(expResult, result);
        instance.unfollow(u2);
        expResult = 0;
        result = instance.getFollowingCount();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAddRemoveFollowedBy() {
        System.out.println("setAddRemoveFollowedBy");
        User instance = new User("name");
        User u2 = new User("name2");
        int expResult = 0;
        int result = instance.getFollowedByCount();
        assertEquals(expResult, result);
        instance.addFollowedBy(instance);
        result = instance.getFollowedByCount();
        assertEquals(expResult, result);
        instance.addFollowedBy(u2);
        expResult = 1;
        result = instance.getFollowedByCount();
        assertEquals(expResult, result);
        instance.removeFollowedBy(u2);
        expResult = 0;
        result = instance.getFollowedByCount();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAddRemoveKweet() {
        System.out.println("setAddRemoveKweet");
        User instance = new User();
        int expResult = 0;
        int result = instance.getKweetCount();
        assertEquals(expResult, result);
        Kweet kweet = new Kweet(instance, "asdf");
        instance.addKweet(kweet);
        expResult = 1;
        result = instance.getKweetCount();
        assertEquals(expResult, result);
        instance.removeKweet(kweet);
        expResult = 0;
        result = instance.getKweetCount();
        assertEquals(expResult, result);
    }
}
