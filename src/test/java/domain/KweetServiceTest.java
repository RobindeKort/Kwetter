package domain;

import domain.User;
import domain.Kweet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;
import java.util.Queue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robin
 */
public class KweetServiceTest {
    final int kweetCount = 10;
    Queue<Kweet> kweets;
    
    @Before
    public void setUp() {
        kweets = new PriorityQueue();
        for (int i = 0; i < kweetCount; i++) {
            kweets.add(new Kweet(String.format("Kweet%02d", i)));
        }
    }
    
    /**
     * Test if Kweet constructor uses correct DatePosted (check month/day/hour)
     */
    @Test
    public void testConstructor() {
        Kweet k = new Kweet("Kweet999");
        Calendar c = new GregorianCalendar();
        assertEquals(c.get(Calendar.MONTH), k.getDatePosted().get(Calendar.MONTH));
        assertEquals(c.get(Calendar.DAY_OF_MONTH), k.getDatePosted().get(Calendar.DAY_OF_MONTH));
        assertEquals(c.get(Calendar.HOUR_OF_DAY), k.getDatePosted().get(Calendar.HOUR_OF_DAY));
    }
    
    @Test
    public void testLike() {
        User u = new User("User999");
        Kweet k = kweets.poll();
        assertEquals(k.getLikeCount(), 0);
        System.out.println(k.getLikeCount());
        k.like(u);
        assertEquals(k.getLikeCount(), 1);
        k.like(u);
        assertEquals(k.getLikeCount(), 1);
    }
    
    @Test
    public void testUnlike() {
        User u = new User("User999");
        Kweet k = kweets.poll();
        k.like(u);
        assertEquals(k.getLikeCount(), 1);
        k.unlike(u);
        assertEquals(k.getLikeCount(), 0);
        k.unlike(u);
        assertEquals(k.getLikeCount(), 0);
    }
}
