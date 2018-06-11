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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Robin
 */
@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "Kweet.findKweetsByBody",
            query = "SELECT k FROM Kweet k WHERE k.body LIKE CONCAT('%', :body, '%')")
})
public class Kweet implements Serializable, Comparable<Kweet> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar datePosted;
    @ManyToOne(fetch=FetchType.LAZY)
    private Account postedBy;
    private String body;
    
    private Set<Account> mentions;
    private Set<Account> likedBy;
    
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
    
    public Kweet(Account postedBy, String body) {
        this(postedBy, body, new HashSet());
    }
    
    public Kweet(Account postedBy, String body, Calendar datePosted) {
        this(postedBy, body, new HashSet(), datePosted);
    }
    
    public Kweet(Account postedBy, String body, Set<Account> mentions) {
        this(postedBy, body, mentions, new GregorianCalendar());
    }
    
    public Kweet(Account postedBy, String body, Set<Account> mentions, Calendar datePosted) {
        this.datePosted = datePosted;
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

    public Account getPostedBy() {
        return postedBy;
    }
    
    public String getBody() {
        return body;
    }
    
    public Set<Account> getMentions() {
        initCollections();
        return Collections.unmodifiableSet(mentions);
    }

    public void like(Account user) {
        initCollections();
        likedBy.add(user);
    }

    public void unlike(Account user) {
        initCollections();
        likedBy.remove(user);
    }

    public int getLikeCount() {
        initCollections();
        return likedBy.size();
    }
    
    public Set<Account> getLikedBy() {
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
    
    @Override
    public String toString() {
        return String.format("%1$s", this.body);
    }
}
