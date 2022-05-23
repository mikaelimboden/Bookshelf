package ch.bzz.skigebiete.model;

public class Vermietung {
    private String vermietungUUID;
    private String vermietungName;
    private String vermietungOrt;
    private String vermietungPLZ;
    private boolean vermietungOffen;
    private String artikelName;
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
