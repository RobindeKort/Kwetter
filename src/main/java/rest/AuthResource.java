package rest;

import domain.Account;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import service.AccountService;
import util.PropertiesProvider;

@Path("/auth")
public class AuthResource {

//    @Inject
    private AccountService accountService;

    @Context
    private UriInfo context;

//    public AccountResource() {
//        
//    }
    @Inject
    public AuthResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLoggedInAccount(@CookieParam("access_token") Cookie cookie) {
        if (cookie == null) {
            return Response
                    .status(Status.FORBIDDEN)
                    .build();
        }
        try {
            verifyToken(cookie.getValue());
            String key = PropertiesProvider.getSecurityKey();
            String username = Jwts.parser().setSigningKey(key).parseClaimsJws(cookie.getValue()).getBody().getSubject();
            Account acc = accountService.getAccount(username);
            return Response
                    .status(Status.OK)
                    .entity(acc)
                    .build();
        } catch (SignatureException se) {
            return Response
                    .status(Status.FORBIDDEN)
                    .build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username,
            @FormParam("password") String password) {
        try {
            Account acc = authenticate(username, password);
            String token = issueToken(username);
            return Response
                    .status(Status.OK)
                    .cookie(new NewCookie(new Cookie("access_token", token), "KwetterAuth", 3600, false))
                    .entity(acc)
                    .build();
        } catch (IllegalArgumentException iae) {
            return Response
                    .status(Status.FORBIDDEN)
                    .build();
        }
    }

    private Account authenticate(String username, String password) throws IllegalArgumentException {
        Account acc = accountService.getAccount(username);
        if (acc == null) {
            throw new IllegalArgumentException("Invalid authentication: Account not found");
        } else if (!acc.checkPassword(password)) {
            throw new IllegalArgumentException("Invalid authentication: Password invalid");
        }
        // No exceptions thrown, user is authenticated
        return acc;
    }

    public static void verifyToken(String token) throws SignatureException {
        String key = PropertiesProvider.getSecurityKey();
        Jwts.parser().setSigningKey(key).parseClaimsJws(token);
    }

    private String issueToken(String username) {
        // Acquire key to sign token
        String key = PropertiesProvider.getSecurityKey();
        // Create the token and compact it to a String
        String compactJws = Jwts.builder()
                .setSubject(username)
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        // Verify the token
        assert Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody().getSubject().equals(username);
        return compactJws;
    }
}
