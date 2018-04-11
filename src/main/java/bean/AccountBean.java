package bean;

import domain.Account;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import service.AccountService;

/**
 *
 * @author Robin
 */
//@Named(value = "userBean")
@ManagedBean
public class AccountBean {

    private final String testString = "testStr";
    @Inject
    private AccountService accountService;

    /**
     * Creates a new instance of UserBean
     */
    public AccountBean() {

    }

    public String gettestString() {
        return testString;
    }

    public String getLoggedInName() {
        return "asdf";
    }

    public String getUsers() {
        String partialName = "dmi";
        StringBuilder sb = new StringBuilder();
        List<Account> accs = accountService.findAccounts(partialName);
        for (Account acc : accs) {
            sb.append("\n<tr>\n<td>");
            sb.append(acc.getUserName());
            sb.append("</td>\n<td>");
            sb.append(acc.getHighestGroup().toString());
            sb.append("</td>\n<td>");
            sb.append("BUTTONS HERE");
            sb.append("</td>\n</tr>");
        }
        return sb.toString();
    }

    public AccountService getAccountService() {
        return accountService;
    }
}
