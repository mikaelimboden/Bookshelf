package ch.bzz.skigebiete.service;

import ch.bzz.skigebiete.data.DataHandler;
import ch.bzz.skigebiete.model.Vermietung;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

}

