package Domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Robin
 */
public class User implements Comparable<User> {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String picturePath;
    private String firstName;
    private String lastName;
    private String bio;
    private String location;
    private String website;
    private Role role;
    
    private Set<User> following;
    private Set<User> followedBy;
    private Set<Kweet> kweets;
    
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
        this.following = new HashSet();
        this.followedBy = new HashSet();
        this.kweets = new HashSet();
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean checkPassword(String password) {
        if (this.password.equals(password)) {
            return true;
        }
        return false;
    }

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
    
    public void addFollowedBy(User following) {
        this.followedBy.add(following);
    }

    public void follow(User toFollow) {
        this.following.add(toFollow);
        toFollow.addFollowedBy(this);
    }
    
    public void removeFollowedBy(User unfollowing) {
        this.followedBy.remove(unfollowing);
    }

    public void unfollow(User toUnfollow) {
        this.following.remove(toUnfollow);
        toUnfollow.removeFollowedBy(this);
    }
    
    public int getFollowingCount() {
        return following.size();
    }

    public Set<User> getFollowing() {
        return Collections.unmodifiableSet(following);
    }
    
    public int getFollowedByCount() {
        return followedBy.size();
    }

    public Set<User> getFollowedBy() {
        return Collections.unmodifiableSet(followedBy);
    }
    
    public void addKweet(Kweet kweet) {
        kweets.add(kweet);
    }
    
    public int getKweetCount() {
        return kweets.size();
    }

    public Set<Kweet> getKweets() {
        return Collections.unmodifiableSet(kweets);
    }

    public void setKweets(Set<Kweet> kweets) {
        this.kweets = kweets;
    }

    @Override
    public int compareTo(User other) {
        return this.userName.compareTo(other.userName);
    }
}
