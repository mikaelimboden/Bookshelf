package ch.bzz.skigebiete.data;

import ch.bzz.skigebiete.model.Skipisten;
import ch.bzz.skigebiete.model.Skigebiet;
import ch.bzz.skigebiete.model.Vermietung;
import ch.bzz.skigebiete.service.Config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Skipisten> skipistenList;
    private List<Skigebiet> skigebietList;
    private List<Vermietung> vermietungList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setSkipistenList(new ArrayList<>());
        readSkipistenJSON();
        setSkigebietList(new ArrayList<>());
        readSkigebietJSON();
        setVermietungList(new ArrayList<>());
        readVermietungJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all Skipisten
     * @return list of Skipisten
     */
    public List<Skipisten> readAllSkipisten() {
        return getSkipistenList();
    }

    /**
     * reads a skipiste by its uuid
     * @param skipistenUUID
     * @return the Skipiste (null=not found)
     */
    public Skipisten readSkipistenByUUID(String skipistenUUID) {
        Skipisten skipisten = null;
        for (Skipisten entry : getSkipistenList()) {
            if (entry.getSkipistenUUID().equals(skipistenUUID)) {
                skipisten = entry;
            }
        }
        return skipisten;
    }

    /**
     * reads all Skigebiete
     * @return list of Skigebiete
     */
    public List<Skigebiet> readAllSkigebiet() {
        return getSkigebietList();
    }

    /**
     * reads an skigebiet by its uuid
     * @param skigebietUUID
     * @return the Skigebiet (null=not found)
     */
    public Skigebiet readSkigebietByUUID(String skigebietUUID) {
        Skigebiet skigebiet = null;
        for (Skigebiet entry : getSkigebietList()) {
            if (entry.getSkigebietUUID().equals(skigebietUUID)) {
                skigebiet = entry;
            }
        }
        return skigebiet;
    }

    /**
     * reads all vermietungs
     * @return list of vermietungs
     */
    public List<Vermietung> readAllVermietung() {

        return getVermietungList();
    }

    /**
     * reads a vermietung by its uuid
     * @param vermietungUUID
     * @return the Vermietung (null=not found)
     */
    public Vermietung readVermietungbyUUID(String vermietungUUID) {
        Vermietung vermietung = null;
        for (Vermietung entry : getVermietungList()) {
            if (entry.getVermietungUUID().equals(vermietungUUID)) {
                vermietung = entry;
            }
        }
        return vermietung;
    }

    /**
     * reads the Skipisten from the JSON-file
     */
    private void readSkipistenJSON() {
        try {
            String path = Config.getProperty("skipistenJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Skipisten[] skipistens = objectMapper.readValue(jsonData, Skipisten[].class);
            for (Skipisten skipisten : skipistens) {
                getSkipistenList().add(skipisten);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the Skigebiet from the JSON-file
     */
    private void readSkigebietJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("skigebietJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Skigebiet[] skigebiets = objectMapper.readValue(jsonData, Skigebiet[].class);
            for (Skigebiet skigebiet : skigebiets) {
                getSkigebietList().add(skigebiet);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the vermietung from the JSON-file
     */
    private void readVermietungJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(
                            Config.getProperty("vermietungJSON")
                    )
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Vermietung[] vermietungs = objectMapper.readValue(jsonData, Vermietung[].class);
            for (Vermietung vermietung : vermietungs) {
                getVermietungList().add(vermietung);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets skipistenList
     *
     * @return value of skipistenList
     */
    private List<Skipisten> getSkipistenList() {
        return skipistenList;
    }

    /**
     * sets skigebietList
     *
     * @param skigebietList the value to set
     */
    private void setSkipistenList(List<Skipisten> skigebietList) {
        this.skipistenList = skigebietList;
    }

    /**
     * gets skigebietList
     *
     * @return value of skigebietList
     */
    private List<Skigebiet> getSkigebietList() {
        return skigebietList;
    }


    /**
     * sets skigebietList
     *
     * @param skigebietList the value to set
     */
    private void setSkigebietList(List<Skigebiet> skigebietList) {
        this.skigebietList = skigebietList;
    }

    /**
     * gets vermietungList
     *
     * @return value of vermietungList
     */
    private List<Vermietung> getVermietungList() {
        return vermietungList;
    }

    /**
     * sets vermietungList
     *
     * @param vermietungList the value to set
     */
    private void setVermietungList(List<Vermietung> vermietungList) {
        this.vermietungList = vermietungList;
    }


}