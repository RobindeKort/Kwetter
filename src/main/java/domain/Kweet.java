package domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Robin
 */
@Entity
@Table
public class Kweet implements Serializable, Comparable<Kweet> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar datePosted;
    @ManyToOne(fetch=FetchType.LAZY)
    private User postedBy;
    private String body;
    
    private Set<User> mentions;
    private Set<User> likedBy;
    
    private void initCollections() {
        if (mentions == null) {
            mentions = new HashSet();
        }
        if (likedBy == null) {
            likedBy = new HashSet();
        }
    }
    
    public Kweet() {
        // Nothing
    }
    
    public Kweet(User postedBy, String body) {
        this(postedBy, body, new HashSet());
    }
    
    public Kweet(User postedBy, String body, Set<User> mentions) {
        this.datePosted = new GregorianCalendar();
        this.postedBy = postedBy;
        this.body = body;
        this.mentions = mentions;
        this.likedBy = new HashSet();
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDatePosted() {
        return datePosted;
    }

    public User getPostedBy() {
        return postedBy;
    }
    
    public String getBody() {
        return body;
    }
    
    public Set<User> getMentions() {
        initCollections();
        return Collections.unmodifiableSet(mentions);
    }

    public void like(User user) {
        initCollections();
        likedBy.add(user);
    }

    public void unlike(User user) {
        initCollections();
        likedBy.remove(user);
    }

    public int getLikeCount() {
        initCollections();
        return likedBy.size();
    }
    
    public Set<User> getLikedBy() {
        initCollections();
        return Collections.unmodifiableSet(likedBy);
    }

    @Override
    public int compareTo(Kweet other) {
        // invert compare to achieve reversed date sort
        int comp = other.datePosted.compareTo(this.datePosted);
        if (comp == 0) {
            return this.body.compareTo(other.body);
        }
        return comp;
    }
}
