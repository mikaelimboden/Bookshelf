package ch.bzz.skigebiete.service;

import ch.bzz.skigebiete.data.UserData;
import ch.bzz.skigebiete.model.User;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

@Path("user")
public class UserService {

    /**
     *
     * @param username
     * @param password
     * @return
     */

    @POST
    @Path("user")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login (
            @FormParam("username") String username,
            @FormParam("password") String password

    )
    {
        int httpStatus;
        User user = UserData.findUser(username, password);
        if (user.getRole().equals("guest"))
            httpStatus = 404;
        else {
            httpStatus = 200;
        }
        NewCookie cookie = new NewCookie(
                "userRole",
                user.getRole(),
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );
        Response response = Response
                .status(httpStatus)
                .entity(" ")
                .build();
                return response;

    }
}
