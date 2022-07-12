package ch.bzz.skigebiete.service;

import ch.bzz.skigebiete.data.DataHandler;
import ch.bzz.skigebiete.model.Skipisten;
import ch.bzz.skigebiete.model.Skigebiet;
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
 * services für skigepisten
 */
@Path("skipisten")
public class SkipistenService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSkipisten(
                    @CookieParam("userRole") String userRole) {
        {
            List<Skigebiet> genre = null;
            int httpStatus;
            if (userRole == null || userRole.equals("guest")) {
                httpStatus = 403;
            } else {
                httpStatus = 200;
            }
        }
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String skipistenUUID
    ) {
        Skipisten skipisten = DataHandler.readSkipistenByUUID(skipistenUUID);
        return Response
                .status(200)
                .entity(skipisten)
                .build();
    }

    /**
     * einfügen von neuer skipiste
     * skipistenName
     * skipistenSchwierigkeitsgrad
     * skipistenOrt
     * skipistenLaenge
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSkipisten(
            @Valid @BeanParam Skipisten skipisten
    ) {

        skipisten.setSkipistenName(UUID.randomUUID().toString());
        DataHandler.insertSkipisten(skipisten);
        return Response
                .status(200)
                .entity("")
                .build();
    }


    /**
     * updates eine Vermietung
     * skipistenUUID
     * skipistenName
     * skipistenSchwierigkeitsgrad
     * skipistenOrt
     * skipistenLaenge
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateVermietung(
            @Valid @BeanParam Skipisten skipisten
    ) {
        int httpStatus = 200;
        Skipisten oldskipisten = DataHandler.readSkipistenByUUID(skipisten.getSkipistenUUID());
        if (skipisten != null) {
            oldskipisten.setSkipistenUUID(skipisten.getSkipistenUUID());
            oldskipisten.setSkipistenName(skipisten.getSkipistenName());
            oldskipisten.setSkipistenSchwierigkeitsgrad(skipisten.getSkipistenSchwierigkeitsgrad());
            oldskipisten.setSkipistenOrt(skipisten.getSkipistenOrt());
            oldskipisten.setSkipistenLaenge(skipisten.getSkipistenLaenge());
            DataHandler.updateSkipisten();
        } else {
            httpStatus = 410;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * löscht eine skipisten identifiziert bei ihrer uuid
     * @param skipistenUUID  the key
     * @return  Response
     */
    @DELETE
    @Path("delete")
    public Response deleteSkipisten(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String skipistenUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteSkipisten(skipistenUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
