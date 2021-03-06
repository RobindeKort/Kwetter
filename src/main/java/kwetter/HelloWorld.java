package kwetter;

import domain.*;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import service.AccountService;

@Path("/users")
public class HelloWorld {

//    @Inject
    private AccountService accountService;

    @Context
    private UriInfo context;
    
//    public HelloWorld() {
//        
//    }

    @Inject
    public HelloWorld(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Path("{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getAccount(@PathParam("userName") String userName) {
        Account u = accountService.getAccount(userName);
        return u;
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postAccount(Account account) {
        if (account != null) {
            accountService.createAccount(account);
        }
        String result = account.getUserName();
        return Response.status(Status.OK).entity(result).build();
    }
}
