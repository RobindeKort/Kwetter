package domain;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robin
 */
public class KweetTest {
    @Test
    public void testConstructorEmpty() {
        System.out.println("constructorEmpty");
        Kweet instance = new Kweet();
        assertEquals(instance.getId(), null);
        assertEquals(instance.getDatePosted(), null);
        assertEquals(instance.getPostedBy(), null);
        assertEquals(instance.getBody(), null);
        assertEquals(instance.getMentions(), Collections.unmodifiableSet(new HashSet<User>()));
        assertEquals(instance.getLikeCount(), 0);
        assertEquals(instance.getLikedBy(), Collections.unmodifiableSet(new HashSet<User>()));
    }
    
    @Test
    public void testConstructorFull() {
        System.out.println("constructorFull");
        User user = new User();
        Set<User> mentions = new HashSet();
        Kweet instance = new Kweet(user, "asdf", mentions);
        Calendar cal = new GregorianCalendar();
        assertEquals(instance.getId(), null);
        assertEquals(instance.getDatePosted().get(Calendar.MONTH), cal.get(Calendar.MONTH));
        assertEquals(instance.getDatePosted().get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(instance.getDatePosted().get(Calendar.HOUR_OF_DAY), cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(instance.getPostedBy(), user);
        assertEquals(instance.getBody(), "asdf");
        assertEquals(instance.getMentions(), Collections.unmodifiableSet(mentions));
        assertEquals(instance.getLikeCount(), 0);
        assertEquals(instance.getLikedBy(), Collections.unmodifiableSet(new HashSet<User>()));
    }
    
    @Test
    public void testSetId() {
        System.out.println("setId");
        Kweet instance = new Kweet();
        instance.setId(2L);
        Long expResult = 2L;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLikeUnlike() {
        System.out.println("likeUnlike");
        User user = new User();
        Kweet instance = new Kweet();
        int expResult = 0;
        int result = instance.getLikeCount();
        assertEquals(expResult, result);
        instance.like(user);
        expResult = 1;
        result = instance.getLikeCount();
        assertEquals(expResult, result);
        instance.like(user);
        result = instance.getLikeCount();
        assertEquals(expResult, result);
        instance.unlike(user);
        expResult = 0;
        result = instance.getLikeCount();
        assertEquals(expResult, result);
        
    }
}
