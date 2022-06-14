package ch.bzz.skigebiete.service;

import ch.bzz.skigebiete.data.DataHandler;
import ch.bzz.skigebiete.model.Skipisten;
import ch.bzz.skigebiete.model.Skigebiet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

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

    /**
     * inserts a new skipiste
     * @param skipistenName
     * @param skipistenSchwierigkeitsgrad
     * @param skipistenOrt
     * @param skipistenLaenge
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSong(
            @FormParam("skipistenName") String skipistenName,
            @FormParam("skipistenSchwierigkeitsgrad") String skipistenSchwierigkeitsgrad,
            @FormParam("skipistenOrt") String skipistenOrt,
            @FormParam("skipistenLaenge") int skipistenLaenge
    ) {
        Skipisten skipisten = new Skipisten();
        skipisten.setSkipistenName(UUID.randomUUID().toString());
        skipisten.setSkipistenSchwierigkeitsgrad(skipistenSchwierigkeitsgrad);
        skipisten.setSkipistenOrt(skipistenOrt);
        skipisten.setSkipistenLaenge(skipistenLaenge);

        DataHandler.insertSkipisten(skipisten);
        return Response
                .status(200)
                .entity("")
                .build();
    }
}
