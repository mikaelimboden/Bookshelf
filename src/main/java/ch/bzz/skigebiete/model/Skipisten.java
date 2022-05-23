package ch.bzz.skigebiete.model;

/**
 * the publisher of one or more books
 */
public class Skipisten {
    private String skipistenUUID;
    private String skipistenName;
    private String skipistenSchwierigkeitsgrad;
    private String skipistenOrt;
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

