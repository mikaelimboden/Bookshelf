package ch.bzz.skigebiete.service;

import ch.bzz.skigebiete.data.DataHandler;
import ch.bzz.skigebiete.model.Skigebiet;
import ch.bzz.skigebiete.model.Skigebiet;
import ch.bzz.skigebiete.model.Skipisten;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * services für skigebiete
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

    /**
     * einfügen von neuer skigebiet
     * @param skigebietName
     * @param skigebietOrt
     * @param skigebietPLZ
     * @param skigebietOffen
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSkigebiet(
            @FormParam("skigebietName") String skigebietName,
            @FormParam("skigebietOrt") String skigebietOrt,
            @FormParam("skigebietPLZ") int skigebietPLZ,
            @FormParam("skigebietOffen") boolean skigebietOffen
    ) {
        Skigebiet skigebiet = new Skigebiet();
        skigebiet.setSkigebietUUID(UUID.randomUUID().toString());
        skigebiet.setSkigebietName(skigebietName);
        skigebiet.setSkigebietOrt(skigebietOrt);
        skigebiet.setSkigebietPLZ(skigebietPLZ);
        skigebiet.setSkigebietOffen(skigebietOffen);

        DataHandler.insertSkigebiet(skigebiet);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates ein Skigebiet
     * @param skigebietUUID
     * @param skigebietName
     * @param skigebietOrt
     * @param skigebietPLZ
     * @param skigebietOffen
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSkigebiet(
            @FormParam("skigebietUUID") String skigebietUUID,
            @FormParam("skigebietName") String skigebietName,
            @FormParam("skigebietOrt") String skigebietOrt,
            @FormParam("skigebietPLZ") int skigebietPLZ,
            @FormParam("skigebietOffen") boolean skigebietOffen
    ) {
        int httpStatus = 200;
        Skigebiet skigebiet = DataHandler.readSkigebietByUUID(skigebietUUID);
        if (skigebiet != null) {
            skigebiet.setSkigebietUUID(skigebietUUID);
            skigebiet.setSkigebietName(skigebietName);
            skigebiet.setSkigebietOrt(skigebietOrt);
            skigebiet.setSkigebietPLZ(skigebietPLZ);
            skigebiet.setSkigebietOffen(skigebietOffen);
            DataHandler.updateSkigebiet();
        } else {
            httpStatus = 410;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * löscht ein skigebiet identifiziert bei ihrer uuid
     * @param skigebietUUID  the key
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteSkigebiet(
            @QueryParam("uuid") String skigebietUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteSkigebiet(skigebietUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
