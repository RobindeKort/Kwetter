package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Robin
 */
@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findUserByUserName",
            query = "SELECT u FROM User u WHERE u.userName = :username"),
    @NamedQuery(name = "User.findUsersByUserName",
            query = "SELECT u FROM User u WHERE u.userName LIKE CONCAT('%', :username, '%')")
})
public class User implements Comparable<User> {

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
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY) @JoinTable(name = "user_following")
    private Set<User> following;
    @OneToMany(fetch = FetchType.LAZY) @JoinTable(name = "user_followedBy")
    private Set<User> followedBy;
    @OneToMany(mappedBy = "postedBy", fetch = FetchType.LAZY)
    private Set<Kweet> kweets;

    private void initCollections() {
        if (following == null) {
            following = new HashSet();
        }
        if (followedBy == null) {
            followedBy = new HashSet();
        }
        if (kweets == null) {
            kweets = new HashSet();
        }
    }

    public User() {
        // Nothing
    }

    public User(String userName) {
        this(userName, "password");
    }

    public User(String userName, String password) {
        this(userName, password, "", "", "", "", "", "", "");
    }

    public User(String userName, String password, String email, String picturePath,
            String firstName, String lastName, String bio, String location,
            String website) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.picturePath = picturePath;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.location = location;
        this.website = website;
        this.role = Role.User;
        initCollections();
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
        if (this.password != null && this.password.equals(password)) {
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
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void follow(User toFollow) {
        initCollections();
        if (toFollow.getUserName().equals(this.getUserName())) {
            return;
        }
        this.following.add(toFollow);
    }

    public void unfollow(User toUnfollow) {
        initCollections();
        this.following.remove(toUnfollow);
    }

    public int getFollowingCount() {
        initCollections();
        return following.size();
    }

    public Set<User> getFollowing() {
        initCollections();
        return Collections.unmodifiableSet(following);
    }

    public void addFollowedBy(User following) {
        initCollections();
        if (following.getUserName().equals(this.getUserName())) {
            return;
        }
        this.followedBy.add(following);
    }

    public void removeFollowedBy(User unfollowing) {
        initCollections();
        this.followedBy.remove(unfollowing);
    }

    public int getFollowedByCount() {
        initCollections();
        return followedBy.size();
    }

    public Set<User> getFollowedBy() {
        initCollections();
        return Collections.unmodifiableSet(followedBy);
    }

    public void addKweet(Kweet kweet) {
        initCollections();
        kweets.add(kweet);
    }

    public void removeKweet(Kweet kweet) {
        initCollections();
        kweets.remove(kweet);
    }

    public int getKweetCount() {
        initCollections();
        return kweets.size();
    }

    public Set<Kweet> getKweets() {
        initCollections();
        return Collections.unmodifiableSet(kweets);
    }

    @Override
    public int compareTo(User other) {
        return this.userName.compareTo(other.userName);
    }
}
