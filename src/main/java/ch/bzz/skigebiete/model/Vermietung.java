package ch.bzz.skigebiete.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

public class Vermietung {
    @FormParam("vermietungUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String vermietungUUID;
    @FormParam("vermietungName")
    @Size(min=5, max=40)
    private String vermietungName;
    @FormParam("vermietungOrt")
    @Size(min=5, max=40)
    private String vermietungOrt;
    @FormParam("vermietungPLZ")
    @Positive
    private String vermietungPLZ;
    @FormParam("vermietungOffen")
    private boolean vermietungOffen;
    @FormParam("artikelName")
    @Size(min=5, max=40)
    private String artikelName;
    @FormParam("artikelNummer")
    @Positive
    private int artikelNummer;

    public String getVermietungName() {
        return vermietungName;
    }

    public void setVermietungName(String vermietungName) {
        this.vermietungName = vermietungName;
    }

    public String getVermietungOrt() {
        return vermietungOrt;
    }

    public void setVermietungOrt(String vermietungOrt) {
        this.vermietungOrt = vermietungOrt;
    }

    public String getVermietungPLZ() {
        return vermietungPLZ;
    }

    public void setVermietungPLZ(String vermietungPLZ) {
        this.vermietungPLZ = vermietungPLZ;
    }

    public boolean isVermietungOffen() {
        return vermietungOffen;
    }

    public void setVermietungOffen(boolean vermietungOffen) {
        this.vermietungOffen = vermietungOffen;
    }

    public String getArtikelName() {
        return artikelName;
    }

    public void setArtikelName(String artikelName) {
        this.artikelName = artikelName;
    }

    public int getArtikelNummer() {
        return artikelNummer;
    }

    public void setArtikelNummer(int artikelNummer) {
        this.artikelNummer = artikelNummer;
    }

    public String getVermietungUUID() {
        return vermietungUUID;
    }

    public void setVermietungUUID(String vermietungUUID) {
        this.vermietungUUID = vermietungUUID;
    }
}
