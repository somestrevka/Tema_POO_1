package query;

import fileio.ActionInputData;
import fileio.Input;
import fileio.MovieInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.util.List;

public class RatingMovie {
    /**
    * query-ul de rating pentru filme
     */
    public void ratingMovie(final Input input, final ActionInputData inputData, final JSONArray arrayResult,
                            final Writer fileWriter) throws java.io.IOException {
        int t = 0, count = 0;
        List<List<String>> filtre = inputData.getFilters();
        String an = filtre.get(0).get(0); // verificam daca exista seriale care respecta conditia
        List<String> genuri = filtre.get(1);
        for (MovieInputData movie: input.getMovies()
        ) {
            if (an != null) {
                if (String.valueOf(movie.getYear()).compareTo(an) == 0) {
                    t = 1;
                    for (int i = 0; i < genuri.size(); i++) {
                        if (!movie.getGenres().contains(genuri.get(i))) {
                            t = 0;
                        }
                    }
                }
                if (t == 1) {
                    count++;
                }
            }
        }
        if (count == 0) {
            arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                    "Query result: []"));
        }
    }
}
