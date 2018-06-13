package dto;

import domain.Account;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
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
    
    private Map<String, String> urls;
    
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
        this.urls = new HashMap();
        this.urls.put("", KwetterApplication.BASE_ANG_URL + "/users/" + userName);
        this.urls.put("", KwetterApplication.BASE_API_URL + "/users/" + userName);
    }
}
