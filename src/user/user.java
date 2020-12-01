package user;

import Video.Videoclip;

import java.util.ArrayList;
import java.util.HashMap;

public class user {
    ArrayList<Videoclip> loved;
    HashMap<String, Videoclip> watched;

    public void view(Videoclip video) {
        if (watched.containsKey(video.title)) {
            watched.get(video.title).number_views++;
        } else {
            video.number_views = 0;
            watched.put(video.title, video);
        }
    }

    public void favorite(Videoclip video) {
        if (watched.containsKey(video.title)) {
            loved.add(video);
        }
    }

    public void rating(Videoclip video) {

    }
}
