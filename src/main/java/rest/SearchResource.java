package rest;

import domain.Kweet;
import java.util.Queue;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import service.AccountService;

/**
 *
 * @author Robin
 */
@Path("/search")
public class SearchResource {
    
    private AccountService accountService;
    
    @Inject
    public SearchResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Path("{query}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKweets(@PathParam("query") String query) {
        Queue<Kweet> kweets = accountService.getKweetService().findKweets(query);
        System.out.println(kweets);
        return Response
                .status(Status.OK)
                .entity(kweets)
                .build();
    }
}
