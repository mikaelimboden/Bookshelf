package ch.bzz.skigebiete.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import java.util.List;

/**
 * a book in our bookshelf
 */
public class Skigebiet {
    @FormParam("skigebietUUID")
    @Pattern(regexp = "|[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String skigebietUUID;
    @FormParam("skigebietName")
    @Size(min=5, max=40)
    private String skigebietName;
    @FormParam("skigebietOrt")
    @Size(min=5, max=40)
    private String skigebietOrt;
    @FormParam("skigebietPLZ")
    @Positive
    private int skigebietPLZ;
    @FormParam("skigebietOffen")
    private boolean skigebietOffen;


    public String getSkigebietName() {
        return skigebietName;
    }

    public void setSkigebietName(String skigebietName) {
        this.skigebietName = skigebietName;
    }

    public String getSkigebietOrt() {
        return skigebietOrt;
    }

    public void setSkigebietOrt(String skigebietOrt) {
        this.skigebietOrt = skigebietOrt;
    }

    public int getSkigebietPLZ() {
        return skigebietPLZ;
    }

    public void setSkigebietPLZ(int skigebietPLZ) {
        this.skigebietPLZ = skigebietPLZ;
    }

    public boolean isSkigebietOffen() {
        return skigebietOffen;
    }

    public void setSkigebietOffen(boolean skigebietOffen) {
        this.skigebietOffen = skigebietOffen;
    }

    public String getSkigebietUUID() {
        return skigebietUUID;
    }

    public void setSkigebietUUID(String skigebietUUID) {
        this.skigebietUUID = skigebietUUID;
    }
}
