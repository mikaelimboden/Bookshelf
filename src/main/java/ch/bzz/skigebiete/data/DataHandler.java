package ch.bzz.skigebiete.data;

import ch.bzz.skigebiete.model.Skipisten;
import ch.bzz.skigebiete.model.Skigebiet;
import ch.bzz.skigebiete.model.Vermietung;
import ch.bzz.skigebiete.service.Config;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * reads and writes the data in the JSON-files
 */
public final class DataHandler {
    private static DataHandler instance = null;
    private static List<Skipisten> skipistenList;
    private static List<Skigebiet> skigebietList;
    private static List<Vermietung> vermietungList;

    /**
     * initialize the lists with the data
     */
    public static void initLists() {
        DataHandler.setSkigebietList(null);
        DataHandler.setSkipistenList(null);
        DataHandler.setVermietungList(null);
    }
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
     * reads all Skipisten
     * @return list of Skipisten
     */
    public static List<Skipisten> readAllSkipisten() {
        return getSkipistenList();
    }

    /**
     * reads a skipiste by its uuid
     * @param skipistenUUID
     * @return the Skipiste (null=not found)
     */
    public static Skipisten readSkipistenByUUID(String skipistenUUID) {
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
    public static List<Skigebiet> readAllSkigebiet() {
        return getSkigebietList();
    }

    /**
     * reads an skigebiet by its uuid
     * @param skigebietUUID
     * @return the Skigebiet (null=not found)
     */
    public static Skigebiet readSkigebietByUUID(String skigebietUUID) {
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
    public static List<Vermietung> readAllVermietung() {

        return getVermietungList();
    }

    /**
     * reads a vermietung by its uuid
     * @param vermietungUUID
     * @return the Vermietung (null=not found)
     */
    public static Vermietung readVermietungbyUUID(String vermietungUUID) {
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
    private static void readSkipistenJSON() {
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
    private static void readSkigebietJSON() {
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
    private static void readVermietungJSON() {
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
    private static List<Skipisten> getSkipistenList() {
        return skipistenList;
    }

    /**
     * sets skigebietList
     *
     * @param skigebietList the value to set
     */
    private static void setSkipistenList(List<Skipisten> skigebietList) {
        this.skipistenList = skigebietList;
    }

    /**
     * gets skigebietList
     *
     * @return value of skigebietList
     */
    private static List<Skigebiet> getSkigebietList() {
        return skigebietList;
    }


    /**
     * sets skigebietList
     *
     * @param skigebietList the value to set
     */
    private static void setSkigebietList(List<Skigebiet> skigebietList) {
        DataHandler.skigebietList = skigebietList;
    }

    /**
     * gets vermietungList
     *
     * @return value of vermietungList
     */
    private static List<Vermietung> getVermietungList() {
        return vermietungList;
    }

    /**
     * sets vermietungList
     *
     * @param vermietungList the value to set
     */
    private static void setVermietungList(List<Vermietung> vermietungList) {
        DataHandler.vermietungList = vermietungList;
    }

    /**
     * inserts a new skigebiet into the skigebietList
     *
     * @param skigebiet the skigebiet to be saved
     */
    public static void insertSkigebiet(Skigebiet skigebiet) {
        getSkigebietList().add(skigebiet);
        writeSkigebietJSON();
    }
    /**
     * updates the skigebietList
     */
    public static void updateSkigebiet() {
        writeSkigebietJSON();
    }
    /**
     * deletes a skigebiet identified by the skigebiet
     * UUID
     * @param skigebietUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteSkigebiet(String skigebietUUID) {
        Skigebiet skigebiet = readSkigebietByUUID(skigebietUUID);
        if (skigebiet != null) {
            getSkigebietList().remove(skigebiet);
            writeSkigebietJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * inserts a new skigebiet into the skigebietList
     *
     * @param skigebiet the skigebiet to be saved
     */
    public static void insertSkigebiet(Skigebiet skigebiet) {
        getSkigebietList().add(skigebiet);
        writeSkigebietJSON();
    }
    /**
     * updates the skigebietList
     */
    public static void updateSkigebiet() {
        writeSkigebietJSON();
    }
    /**
     * deletes a skigebiet identified by the skigebiet
     * UUID
     * @param skigebietUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteSkigebiet(String skigebietUUID) {
        Skigebiet skigebiet = readSkigebietByUUID(skigebietUUID);
        if (skigebiet != null) {
            getSkigebietList().remove(skigebiet);
            writeSkigebietJSON();
            return true;
        } else {
            return false;
        }
    }


    /**
     * inserts a new skipiste into the skigpistenList
     *
     * @param skipisten the skipiste to be saved
     */
    public static void insertSkipisten(Skipisten skipisten) {
        getSkipistenList().add(skipisten);
        writeSkipistenJSON();
    }
    /**
     * updates the skipistenList
     */
    public static void updateSkipisten() {
        writeSkipistenJSON();
    }
    /**
     * deletes a skipiste identified by the skipisten
     * UUID
     * @param skipistenUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteSkipisten(String skipistenUUID) {
        Skipisten skipisten = readSkipistenByUUID(skipistenUUID);
        if (skipisten != null) {
            getSkipistenList().remove(skipisten);
            writeSkipistenJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * inserts a new vermietung into the vermietungList
     *
     * @param vermietung the vermietung to be saved
     */
    public static void insertVermietung(Vermietung vermietung) {
        getVermietungList().add(vermietung);
        writeVermietungJSON();
    }
    /**
     * updates the vermietungList
     */
    public static void updateVermietung() {
        writeVermietungJSON();
    }
    /**
     * deletes a vermietung identified by the vermietung
     * UUID
     * @param vermietungUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteVermietung(String vermietungUUID) {
        Vermietung vermietung = readVermietungbyUUID(vermietungUUID);
        if (vermietung != null) {
            getSkigebietList().remove(vermietung);
            writeVermietungJSON();
            return true;
        } else {
            return false;
        }
    }
}