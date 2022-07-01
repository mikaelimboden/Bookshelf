package ch.bzz.skigebiete.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * the publisher of one or more books
 */
public class Skipisten {
    @FormParam("skipistenUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String skipistenUUID;
    @FormParam("skipistenName")
    @Size(min=5, max=40)
    private String skipistenName;
    @FormParam("skipistenSchwierigkeitsgrad")
    @Size(min=3, max=7)
    private String skipistenSchwierigkeitsgrad;
    @FormParam("skipistenOrt")
    @Size(min=5, max=40)
    private String skipistenOrt;
    @FormParam("skipistenLaenge")
    private int skipistenLaenge;

    public String getSkipistenUUID() {
        return skipistenUUID;
    }

    public void setSkipistenUUID(String skipistenUUID) {
        this.skipistenUUID = skipistenUUID;
    }

    public String getSkipistenName() {
        return skipistenName;
    }

    public void setSkipistenName(String skipistenName) {
        this.skipistenName = skipistenName;
    }

    public String getSkipistenSchwierigkeitsgrad() {
        return skipistenSchwierigkeitsgrad;
    }

    public void setSkipistenSchwierigkeitsgrad(String skipistenSchwierigkeitsgrad) {
        this.skipistenSchwierigkeitsgrad = skipistenSchwierigkeitsgrad;
    }

    public String getSkipistenOrt() {
        return skipistenOrt;
    }

    public void setSkipistenOrt(String skipistenOrt) {
        this.skipistenOrt = skipistenOrt;
    }

    public int getSkipistenLaenge() {
        return skipistenLaenge;
    }

    public void setSkipistenLaenge(int skipistenLaenge) {
        this.skipistenLaenge = skipistenLaenge;
    }
}

