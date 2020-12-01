package user;

import video.Videoclip;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private ArrayList<Videoclip> loved;
    private HashMap<String, Videoclip> watched;
    /**
    * getter-ul lui loved
     */
    public ArrayList<Videoclip> getLoved() {
        return loved;
    }
    /**
     * initializarea lui loved
     */
    public void makeLoved() {
        this.loved = new ArrayList<>();
    }
    /**
     * getter-ul lui watched
     */
    public HashMap<String, Videoclip> getWatched() {
        return watched;
    }
    /**
     * initializarea lui watched
     */
    public void makeWatched() {
        this.watched = new HashMap<>();
    }
    /**
     * marcam viedoclipul primit ca vazut
     */
    public void view(final Videoclip video) {
        if (watched.containsKey(video.title)) {
            watched.get(video.title).number_views++;
        } else {
            video.number_views = 0;
            watched.put(video.title, video);
        }
    }
    /**
     * adaugarea unui videoclip la lista de favorite
     */
    public void favorite(final Videoclip video) {
        if (watched.containsKey(video.title)) {
            loved.add(video);
        }
    }
    /**
     * recenzie pentru video
     */
    public void rating(final Videoclip video) {

    }
}
