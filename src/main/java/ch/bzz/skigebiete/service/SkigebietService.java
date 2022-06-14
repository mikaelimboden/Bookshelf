package ch.bzz.skigebiete.service;

import ch.bzz.skigebiete.data.DataHandler;
import ch.bzz.skigebiete.model.Skigebiet;
import ch.bzz.skigebiete.model.Skigebiet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * services for reading skigebiete
 */
@Path("skigebiet")
public class SkigebietService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSkigebiet() {
        List<Skigebiet> skigebietList = DataHandler.readAllSkigebiet();
        return Response
                .status(200)
                .entity(skigebietList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readSkigebiet(
            @QueryParam("uuid") String skigebietUUID
    ) {
        Skigebiet skigebiet = DataHandler.readSkigebietByUUID(skigebietUUID);
        return Response
                .status(200)
                .entity(skigebiet)
                .build();
    }

}
