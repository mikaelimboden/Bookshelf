package ch.bzz.skigebiete.service;

import ch.bzz.skigebiete.data.DataHandler;
import ch.bzz.skigebiete.model.Skigebiet;
import ch.bzz.skigebiete.model.Skigebiet;
import ch.bzz.skigebiete.model.Skipisten;
import ch.bzz.skigebiete.model.Vermietung;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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
     * skigebietName
     * skigebietOrt
     * skigebietPLZ
     * skigebietOffen
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSkigebiet(
            @Valid @BeanParam Skigebiet skigebiet
    ) {

        skigebiet.setSkigebietUUID(UUID.randomUUID().toString());
        DataHandler.insertSkigebiet(skigebiet);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates ein Skigebiet
     * skigebietUUID
     * skigebietName
     * skigebietOrt
     * skigebietPLZ
     * skigebietOffen
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateSkigebiet(
            @Valid @BeanParam Skigebiet skigebiet
    ) {
        int httpStatus = 200;
        Skigebiet oldskigebiet = DataHandler.readSkigebietByUUID(skigebiet.getSkigebietUUID());
        if (skigebiet != null) {
            oldskigebiet.setSkigebietName(skigebiet.getSkigebietName());
            oldskigebiet.setSkigebietOrt(skigebiet.getSkigebietOrt());
            oldskigebiet.setSkigebietPLZ(skigebiet.getSkigebietPLZ());
            oldskigebiet.setSkigebietOffen(skigebiet.isSkigebietOffen());
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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
