package dto;

import domain.Account;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import rest.KwetterApplication;

/**
 *
 * @author Robin
 */
public class AccountDTO implements Serializable {
    
    private Long id;
    private String userName;
    private String email;
    private String picturePath;
    private String firstName;
    private String lastName;
    private String bio;
    private String location;
    private String website;
    private String highestGroup;
    
    private Set<String> urls;
    
    public AccountDTO(Account account) {
        this.id = account.getId();
        this.userName = account.getUserName();
        this.email = account.getEmail();
        this.picturePath = account.getPicturePath();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.bio = account.getBio();
        this.location = account.getLocation();
        this.website = account.getWebsite();
        this.highestGroup = account.getHighestGroup().toString();
        this.urls = new HashSet();
        //this.urls.put("", KwetterApplication.BASE_ANG_URL + "/users/" + userName);
        this.urls.add(KwetterApplication.BASE_API_URL + "/users/" + userName);
        this.urls.add(KwetterApplication.BASE_API_URL + "/users/" + userName + "/following");
        this.urls.add(KwetterApplication.BASE_API_URL + "/users/" + userName + "/following/kweets");
        this.urls.add(KwetterApplication.BASE_API_URL + "/users/" + userName + "/followedBy");
        this.urls.add(KwetterApplication.BASE_API_URL + "/users/" + userName + "/kweets");
        //this.urls.add(KwetterApplication.BASE_API_URL + "/auth/kweet");
    }
}
