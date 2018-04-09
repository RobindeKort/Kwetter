package bean;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import service.UserService;

/**
 *
 * @author Robin
 */
//@Named(value = "userBean")
@ManagedBean
public class UserBean {
    
    private final String testString = "testStr";
    private UserService userService;

    /**
     * Creates a new instance of UserBean
     */
    @Inject
    public UserBean() {
        this.userService = new UserService();
    }
    
    public String gettestString() {
        return testString;
    }
    
    public UserService getUserService() {
        return userService;
    }
}
