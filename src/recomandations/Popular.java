package recomandations;


import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import fileio.Writer;
import fileio.MovieInputData;
import fileio.SerialInputData;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Popular {
    /**
     * cautam primul video nevizualizat, de popularitatea cea mai mare
     */
    public void popRec(final Input input, final ActionInputData inputData,
                       final JSONArray arrayResult, final Writer fileWriter)
            throws java.io.IOException {
        HashMap<String, Integer> genuri = new HashMap<>();
        HashMap<String, Integer> filmeVazute = new HashMap<>();  // retin filmele si numarul
                                                                  // total de vizualizari

        for (UserInputData user: input.getUsers()) {
            for (Map.Entry<String, Integer> watched: user.getHistory().entrySet()) {
                if (!filmeVazute.containsKey(watched.getKey())) {
                    filmeVazute.put(watched.getKey(), watched.getValue());
                } else {
                    filmeVazute.put(watched.getKey(), filmeVazute.get(watched.getKey())
                            + watched.getValue());
                }
            }
        }

        for (MovieInputData movie: input.getMovies()) {
            if (filmeVazute.containsKey(movie.getTitle())) {
                for (String gen: movie.getGenres()) {
                    if (genuri.containsKey(gen)) {
                        genuri.put(gen, genuri.get(gen) + filmeVazute.get(movie.getTitle()));
                    } else {
                        genuri.put(gen, filmeVazute.get(movie.getTitle()));
                    }
                }
            }

        }
        ArrayList<Map.Entry<String, Integer>> listaFin = new ArrayList<>();
        for (Map.Entry<String, Integer> intrare: genuri.entrySet()
        ) {
            listaFin.add(intrare);
        }


            listaFin.sort(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(final Map.Entry<String, Integer> o1,
                                   final Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
        UserInputData userAdev = null;
        for (UserInputData user: input.getUsers()
             ) {
            if (user.getUsername().compareTo(inputData.getUsername()) == 0) {
                userAdev = user;
                break;
            }
        }
        for (Map.Entry<String, Integer> entry: genuri.entrySet()) {
            // afisam cel mai popular film din genul cerut
            for (MovieInputData movie: input.getMovies()
                 ) {
                if (movie.getGenres().contains(entry.getKey())) {
                    if (!userAdev.getHistory().containsKey(movie.getTitle())) {
                        arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                                "PopularRecommendation result: " + movie.getTitle()));
                        return;
                    }
                }
            }
            // daca nu s-a gasit niciun film, afisam cel mai popular serial din genul cerut
            for (SerialInputData serial: input.getSerials()
                 ) {
                if (serial.getGenres().contains(entry.getKey())) {
                    if (!userAdev.getHistory().containsKey(serial.getTitle())) {
                        arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                                "PopularRecommendation result: " + serial.getTitle()));
                        return;
                    }
                }
            }
        }
    }
}
