package domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Robin
 */
@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "Account.findAccountByUserName",
            query = "SELECT a FROM Account a WHERE a.userName = :username")
    ,
    @NamedQuery(name = "Account.findAccountsByUserName",
            query = "SELECT a FROM Account a WHERE a.userName LIKE CONCAT('%', :username, '%')")
})
public class Account implements Serializable, Comparable<Account> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String userName;
    private String password;
    @Column(unique = true)
    private String email;
    private String picturePath;
    private String firstName;
    private String lastName;
    private String bio;
    private String location;
    private String website;
    @ManyToMany(mappedBy = "accounts", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<UserGroup> groups = new HashSet();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_following")
    private Set<Account> following = new HashSet();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_followedBy")
    private Set<Account> followedBy = new HashSet();
    @OneToMany(mappedBy = "postedBy", fetch = FetchType.LAZY)
    private Set<Kweet> kweets = new HashSet();

//    private void initCollections() {
//        if (groups == null) {
//            groups = new HashSet();
//            groups.add(new UserGroup("User"));
//        }
//        if (following == null) {
//            following = new HashSet();
//        }
//        if (followedBy == null) {
//            followedBy = new HashSet();
//        }
//        if (kweets == null) {
//            kweets = new HashSet();
//        }
//    }

    public Account() {
        // Nothing
    }

    public Account(String userName, String email, UserGroup group) {
        this(userName, "password", email, group);
    }

    public Account(String userName, String password, String email, UserGroup group) {
        this(userName, password, email, "", "", "", "", "", "", group);
    }

    public Account(String userName, String password, String email, String picturePath,
            String firstName, String lastName, String bio, String location,
            String website, UserGroup group) {
        this.userName = userName;
        this.password = DigestUtils.sha256Hex(password);
        this.email = email;
        this.picturePath = picturePath;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.location = location;
        this.website = website;
        addUserGroup(group);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Check if the given password matches the current password.
     *
     * @param password string that gets compared to current password.
     * @return true if the strings match, false otherwise.
     */
    public boolean checkPassword(String password) {
        if (this.password != null && this.password.equals(DigestUtils.sha256Hex(password))) {
            return true;
        }
        return false;
    }

    /**
     * Update the user's password if the old password matches.
     *
     * @param oldPassword string that has to match the current password.
     * @param newPassword string to which the password will be changed.
     * @return true if the password has been changed, false otherwise.
     */
    public boolean setPassword(String oldPassword, String newPassword) {
        if (this.password.equals(DigestUtils.sha256Hex(oldPassword))) {
            this.password = DigestUtils.sha256Hex(newPassword);
            return true;
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    
    @JsonbTransient
    public Set<UserGroup> getGroups() {
        return this.groups;
    }
    
    public UserGroup getHighestGroup() {
        UserGroup highestGroup = new UserGroup("User");
        for (UserGroup g : groups) {
            if (g.compareTo(highestGroup) > 0) {
                highestGroup = g;
            }
        }
        return highestGroup;
    }
    
    public void addUserGroup(UserGroup group) {
        if (group == null || groups.contains(group)) {
            return;
        }
        // TODO: for some reason the contains method doesn't use the @Override 
        // implementation of UserGroup.equals(Object obj)
        for (UserGroup g : groups) {
            if (g.compareTo(group) == 0) {
                return;
            }
        }
        groups.add(group);
        group.addAccount(this);
    }
    
    public void removeUserGroup(UserGroup group) {
        if (group == null || group.toString().equals("User") || !groups.contains(group)) {
            return;
        }
        groups.remove(group);
        group.removeAccount(this);
    }
    
    public String promoteString() {
        String newGroup;
        UserGroup highestGroup = getHighestGroup();
        if (highestGroup.getGroupName().equals("User")) {
            newGroup = "Moderator";
        }
        else if (highestGroup.getGroupName().equals("Moderator")) {
            newGroup = "Administrator";
        }
        else {
            newGroup = "Administrator";
        }
        return newGroup;
    }
    
//    public String demoteString() {
//        String newGroup;
//        UserGroup highestGroup = getHighestGroup();
//        if (highestGroup.getGroupName().equals("Administrator")) {
//            newGroup = "Moderator";
//        }
//        else if (highestGroup.getGroupName().equals("Moderator")) {
//            newGroup = "User";
//        }
//        else {
//            newGroup = "User";
//        }
//        return newGroup;
//    }

    public void addFollowing(Account toFollow) {
        if (toFollow.getUserName().equals(this.getUserName())) {
            return;
        }
        this.following.add(toFollow);
        toFollow.addFollowedBy(this);
    }

    public void removeFollowing(Account toUnfollow) {
        this.following.remove(toUnfollow);
    }

    public int getFollowingCount() {
        return following.size();
    }

    @JsonbTransient
    public Set<Account> getFollowing() {
        return Collections.unmodifiableSet(following);
    }

    public void addFollowedBy(Account following) {
        if (following.getUserName().equals(this.getUserName())) {
            return;
        }
        this.followedBy.add(following);
    }

    public void removeFollowedBy(Account unfollowing) {
        this.followedBy.remove(unfollowing);
    }

    public int getFollowedByCount() {
        return followedBy.size();
    }

    @JsonbTransient
    public Set<Account> getFollowedBy() {
        return Collections.unmodifiableSet(followedBy);
    }

    public void addKweet(Kweet kweet) {
        kweets.add(kweet);
    }

    public void removeKweet(Kweet kweet) {
        kweets.remove(kweet);
    }

    public int getKweetCount() {
        return kweets.size();
    }

    @JsonbTransient
    public Set<Kweet> getKweets() {
        return Collections.unmodifiableSet(kweets);
    }

    @Override
    public int compareTo(Account other) {
        return this.userName.compareTo(other.userName);
    }
}
