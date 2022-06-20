package ch.bzz.skigebiete.service;

import ch.bzz.skigebiete.data.DataHandler;
import ch.bzz.skigebiete.model.Vermietung;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * services for reading vermietung
 */
@Path("vermietung")
public class VermietungService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listVermietung() {
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
            @QueryParam("uuid") String vermietungUUID
    ) {
        Vermietung vermietung = DataHandler.readVermietungbyUUID(vermietungUUID);
        return Response
                .status(200)
                .entity(vermietung)
                .build();
    }

    /**
     * inserts a new song
     * @param vermietungName
     * @param vermietungOrt
     * @param vermietungPLZ
     * @param vermietungOffen
     * @param artikelName
     * @param artikelNummer
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertSong(
            @FormParam("vermietungName") String vermietungName,
            @FormParam("vermietungOrt") String vermietungOrt,
            @FormParam("vermietungPLZ") String vermietungPLZ,
            @FormParam("vermietungOffen") boolean vermietungOffen,
            @FormParam("artikelName") String artikelName,
            @FormParam("artikelNummer") int artikelNummer
    ) {
        Vermietung vermietung = new Vermietung();
        vermietung.setVermietungName(UUID.randomUUID().toString());
        vermietung.setVermietungOrt(vermietungOrt);
        vermietung.setVermietungPLZ(vermietungPLZ);
        vermietung.setVermietungOffen(vermietungOffen);
        vermietung.setArtikelName(artikelName);
        vermietung.setArtikelNummer(artikelNummer);

        DataHandler.insertVermietung(vermietung);
        return Response
                .status(200)
                .entity("")
                .build();
    }



    /**
     * updates a Vermietung
     * @param vermietungUUID
     * @param vermietungName
     * @param vermietungOrt
     * @param vermietungPLZ
     * @param vermietungOffen
     * @param artikelName
     * @param artikelNummer
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateVermietung(
            @FormParam("vermietungUUID") String vermietungUUID,
            @FormParam("vermietungName") String vermietungName,
            @FormParam("vermietungOrt") String vermietungOrt,
            @FormParam("vermietungPLZ") String vermietungPLZ,
            @FormParam("vermietungOffen") boolean vermietungOffen,
            @FormParam("artikelName") String artikelName,
            @FormParam("artikelNummer") int artikelNummer
    ) {
        int httpStatus = 200;
        Vermietung vermietung = DataHandler.readVermietungbyUUID(vermietungUUID);
        if (vermietung != null) {
            vermietung.setVermietungUUID(vermietungUUID);
            vermietung.setVermietungName(vermietungName);
            vermietung.setVermietungOrt(vermietungOrt);
            vermietung.setVermietungPLZ(vermietungPLZ);
            vermietung.setVermietungOffen(vermietungOffen);
            vermietung.setArtikelName(artikelName);
            vermietung.setArtikelNummer(artikelNummer);
            DataHandler.updateVermietung();
        } else {
            httpStatus = 410;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }





}

