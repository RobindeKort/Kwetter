package domain;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Robin
 */
public class Kweet implements Comparable<Kweet> {
    private Long id;
    private Calendar datePosted;
    private String body;
    
    private Set<User> mentions;
    private Set<User> likedBy;
    
    public Kweet() {
        // Nothing
    }
    
    public Kweet(String body) {
        this(body, new HashSet());
    }
    
    public Kweet(String body, Set<User> mentions) {
        datePosted = new GregorianCalendar();
        this.body = body;
        this.mentions = mentions;
        likedBy = new HashSet();
    }
    
    public Long getId() {
        return id;
    }

    public Calendar getDatePosted() {
        return datePosted;
    }
    
    public String getBody() {
        return body;
    }
    
    public Set<User> getMentions() {
        return Collections.unmodifiableSet(mentions);
    }

    public void like(User user) {
        likedBy.add(user);
    }

    public void unlike(User user) {
        likedBy.remove(user);
    }

    public int getLikeCount() {
        return likedBy.size();
    }
    
    public Set<User> getLikedBy() {
        return Collections.unmodifiableSet(likedBy);
    }

    @Override
    public int compareTo(Kweet other) {
        int comp = this.datePosted.compareTo(other.datePosted);
        if (comp == 0) {
            return this.body.compareTo(other.body);
        }
        return comp;
    }
}
