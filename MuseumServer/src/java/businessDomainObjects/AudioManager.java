package businessDomainObjects;

import java.sql.SQLException;
import java.util.ArrayList;
import persistance.PersistanceRepositoryAudio;
import persistance.PersistanceRepositoryExhibit;
import utility.InputValidator;

/**
 *
 * @author Oliver Brooks <oliver2.brooks@live.uwe.ac.uk>
 */
public class AudioManager {

    private ArrayList<Audio> listOfAudio;

    public ArrayList<Audio> getListOfAudio() {
        return listOfAudio;
    }
    private PersistanceRepositoryAudio persistance;

    public AudioManager(PersistanceRepositoryAudio pr) {
        persistance = pr;
        this.listOfAudio = persistance.getAllAudio();
    }

    public synchronized boolean addAudio(String name, String location) {
        name = InputValidator.clean(name);

        for (Audio audio : listOfAudio) {
            if (audio.getAudioLocation().contains(location) && audio.getAudioName().equals(name)) {
                return false;
            }
        }

        try {
            boolean ret = persistance.addAudio(name, location);
            if (ret == false) {
                return false;
            } else {
                this.listOfAudio = persistance.getAllAudio();
                return true;
            }
        } catch (SQLException se) {
            return false;
        }
    }

    public synchronized boolean removeAudio(int ID) {
        try {
            boolean ret = persistance.removeAudio(ID);
            if (ret == false) {
                return false;
            } else {
                this.listOfAudio = persistance.getAllAudio();
                return true;
            }
        } catch (SQLException se) {
            return false;
        }
    }
}