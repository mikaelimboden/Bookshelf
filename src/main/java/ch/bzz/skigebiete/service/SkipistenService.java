package ch.bzz.skigebiete.service;

import ch.bzz.skigebiete.data.DataHandler;
import ch.bzz.skigebiete.model.Skipisten;
import ch.bzz.skigebiete.model.Skigebiet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * services for reading skigepisten
 */
@Path("skipisten")
public class SkipistenService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSkipisten() {
        List<Skipisten> skipistenList = DataHandler.readAllSkipisten();
        return Response
                .status(200)
                .entity(skipistenList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readSkipisten(
            @QueryParam("uuid") String skipistenUUID
    ) {
        Skipisten skipisten = DataHandler.readSkipistenByUUID(skipistenUUID);
        return Response
                .status(200)
                .entity(skipisten)
                .build();
    }

}
