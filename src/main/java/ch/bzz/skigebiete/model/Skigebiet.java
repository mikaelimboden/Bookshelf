package ch.bzz.skigebiete.model;

import ch.bzz.skigebiete.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import java.util.ArrayList;
import java.util.List;

/**
 * a book in our bookshelf
 */
public class Skigebiet {
    @JsonIgnore
    private List<Skipisten> skipisten;

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

    public void setSkipistenUUID(List<String> uuidList) {
        this.setSkipistenList(new ArrayList<>());
        for (String uuid : uuidList) {
            Skipisten skipiste = DataHandler.readSkipistenByUUID(uuid);
            this.getSkipistenList().add(skipiste);
        }
    }

    @JsonIgnore
    public String getSkipisten() {
        StringBuilder skipisten = new StringBuilder();
        if (this.getSkipistenList() != null) {
            List<String> uuidList = new ArrayList<>();
            for (Skipisten skipiste : this.getSkipistenList()) {
                skipisten.append(skipiste.getSkipistenName()).append(", ");
                skipisten.append(skipiste.getSkipistenSchwierigkeitsgrad()).append(", ");
                skipisten.append(skipiste.getSkipistenOrt()).append(", ");
                skipisten.append(skipiste.getSkipistenLaenge()).append(", ");
            }
        }
        return (skipisten.length() == 0)
                ? null
                : (skipisten.substring(0, skipisten.length() - 2));
    }

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

    public void setSkipistenList(List<Skipisten> skipisten) {
        this.skipisten = skipisten;
    }
    public List<Skipisten> getSkipistenList() {
        return skipisten;
    }

}
