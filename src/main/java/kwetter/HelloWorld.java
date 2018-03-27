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
import service.UserService;

@Path("/users")
public class HelloWorld {

//    @Inject
    private UserService userService;

    @Context
    private UriInfo context;
    
//    public HelloWorld() {
//        
//    }

    @Inject
    public HelloWorld(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("userName") String userName) {
        User u = userService.getUser(userName);
        return u;
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postUser(User user) {
        if (user != null) {
            userService.createUser(user);
        }
        String result = user.getUserName();
        return Response.status(Status.OK).entity(result).build();
    }
}
