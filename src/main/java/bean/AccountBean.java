package bean;

import domain.Account;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import service.AccountService;

/**
 *
 * @author Robin
 */
//@Named(value = "userBean")
@ManagedBean
@SessionScoped
public class AccountBean {
    
    private String accountName;
    private Account account;
    private String searchString;
    private List<Account> accountList;
    
    @Inject
    private AccountService accountService;

    /**
     * Creates a new instance of UserBean
     */
    public AccountBean() {

    }

    public String getAccountName() {
        accountName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        account = accountService.getAccount(accountName);
        return accountName;
    }
    
    public String logOut() {
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        return "login";
    }

//    public String getUsers() {
//        String partialName = "dmi";
//        StringBuilder sb = new StringBuilder();
//        List<Account> accs = accountService.findAccounts(partialName);
//        for (Account acc : accs) {
//            sb.append("\n<tr>\n<td>");
//            sb.append(acc.getUserName());
//            sb.append("</td>\n<td>");
//            sb.append(acc.getHighestGroup().toString());
//            sb.append("</td>\n<td>");
//            sb.append("BUTTONS HERE");
//            sb.append("</td>\n</tr>");
//        }
//        return sb.toString();
//    }
    
    public String getSearchString() {
        return searchString;
    }
    
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
    
    public void findAccounts() {
        if (searchString == null || searchString.length() < 2) {
            return;
        }
        accountList = accountService.findAccounts(searchString);
    }
    
    public List<Account> getAccountList() {
        return accountList;
    }
}
