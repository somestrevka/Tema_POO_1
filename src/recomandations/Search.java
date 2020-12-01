package recomandations;

import fileio.*;
import org.json.simple.JSONArray;

import java.util.ArrayList;

public class Search {
    /**
    * cautam toate videoclipurile nevazute de utilizator
     */
    public void cautam(final Input input, final ActionInputData inputData,
                       final JSONArray arrayResult, final Writer fileWriter)
            throws java.io.IOException {
        // cautam daca videoclipuri care sa respecte criteriile exista
        ArrayList<String> listaVideo = new ArrayList<>();
        int t = 0;
        for (MovieInputData movie: input.getMovies()
             ) {
            if (movie.getGenres().contains(inputData.getGenre())) {
                listaVideo.add(movie.getTitle());
            }
        }
        for (SerialInputData serial: input.getSerials()
             ) {
            if (serial.getGenres().contains(inputData.getGenre())) {
                listaVideo.add(serial.getTitle());
            }
        }
        for (UserInputData user: input.getUsers()
             ) {
            if (user.getUsername().compareTo(inputData.getUsername()) == 0) {
                for (int i = 0; i < listaVideo.size(); i++) {
                    if (!user.getHistory().containsKey(listaVideo.get(i))) {
                        t = 1;
                    }
                }
            }
        }
        if (t == 0) {
            arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                    "SearchRecommendation cannot be applied!"));
        }
    }
}
