package ch.bzz.skigebiete.model;

import java.util.List;

/**
 * a book in our bookshelf
 */
public class Skigebiet {
    private String skigebietUUID;
    private String skigebietName;
    private String skigebietOrt;
    private int skigebietPLZ;
    private boolean skigebietOffen;
    private List<Skipisten> skipisten;


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
        skigebietOrt = skigebietOrt;
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

    public List<Skipisten> getSkipisten() {
        return skipisten;
    }

    public void setSkipisten(List<Skipisten> skipisten) {
        this.skipisten = skipisten;
    }

    public String getSkigebietUUID() {
        return skigebietUUID;
    }

    public void setSkigebietUUID(String skigebietUUID) {
        this.skigebietUUID = skigebietUUID;
    }
}
