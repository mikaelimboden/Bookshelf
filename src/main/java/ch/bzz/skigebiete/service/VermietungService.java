package ch.bzz.skigebiete.service;

import ch.bzz.skigebiete.data.DataHandler;
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
 * services für vermietung
 */
@Path("vermietung")
public class VermietungService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listVermietung(
            @CookieParam("userRole") String userRole) {
        {
            List<Vermietung> genre = null;
            int httpStatus;
            if (userRole == null || userRole.equals("guest")) {
                httpStatus = 403;
            } else {
                httpStatus = 200;
            }
        }
        List<Vermietung> vermietungList = DataHandler.readAllVermietung();
        return Response
                .status(200)
                .entity(vermietungList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readVermietung(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String vermietungUUID
    ) {
        Vermietung vermietung = DataHandler.readVermietungbyUUID(vermietungUUID);
        return Response
                .status(200)
                .entity(vermietung)
                .build();
    }

    /**
     * einfügen von neuer vermietung
     * vermietungName
     * vermietungOrt
     * vermietungPLZ
     * vermietungOffen
     * artikelName
     * artikelNummer
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertVermietung(
            @Valid @BeanParam Vermietung vermietung,

            @NotEmpty
            @Pattern(regexp= "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("skigebietUUID") String skigebietUUID
    ) {

        vermietung.setVermietungName(UUID.randomUUID().toString());
        vermietung.setSkigebietUUID(skigebietUUID);
        DataHandler.insertVermietung(vermietung);
        return Response
                .status(200)
                .entity("")
                .build();
    }



    /**
     * updates eine Vermietung
     * vermietungUUID
     * vermietungName
     * vermietungOrt
     * vermietungPLZ
     * vermietungOffen
     * artikelName
     * artikelNummer
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateVermietung(
            @Valid @BeanParam Vermietung vermietung,

            @NotEmpty
            @Pattern(regexp= "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("skigebietUUID") String skigebietUUID
    ) {
        int httpStatus = 200;
        Vermietung oldvermietung = DataHandler.readVermietungbyUUID(vermietung.getVermietungUUID());
        if (vermietung != null) {
            oldvermietung.setVermietungUUID(vermietung.getVermietungUUID());
            oldvermietung.setVermietungName(vermietung.getVermietungName());
            oldvermietung.setVermietungOrt(vermietung.getVermietungOrt());
            oldvermietung.setVermietungPLZ(vermietung.getVermietungPLZ());
            oldvermietung.setVermietungOffen(vermietung.isVermietungOffen());
            oldvermietung.setArtikelName(vermietung.getArtikelName());
            oldvermietung.setArtikelNummer(vermietung.getArtikelNummer());
            oldvermietung.setSkigebietUUID(skigebietUUID);
            DataHandler.updateVermietung();
        } else {
            httpStatus = 410;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * löscht eine vermietung identifiziert bei iher uuid
     * @param vermietungUUID  the key
     * @return  Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteVermietung(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String vermietungUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteVermietung(vermietungUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }



}

