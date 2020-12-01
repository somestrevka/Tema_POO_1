package recomandations;


import fileio.ActionInputData;
import fileio.Input;
import fileio.MovieInputData;
import fileio.Writer;
import fileio.SerialInputData;
import fileio.UserInputData;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FavRec {
    /**
    * gasim videoul cel mai intalnit in listele de favorite
     */
    public void recomandFav(final Input input, final ActionInputData inputData,
                            final JSONArray arrayResult, final Writer fileWriter)
            throws java.io.IOException {

        HashMap<String, Integer> listaFav = new HashMap<>();
        // facem o mapare a tuturor videoclipurilor
        for (MovieInputData movie: input.getMovies()
             ) {
            if (!listaFav.containsKey(movie.getTitle())) {
                listaFav.put(movie.getTitle(), 0);
            }
        }
        for (SerialInputData serial: input.getSerials()
             ) {
            if (!listaFav.containsKey(serial.getTitle())) {
                listaFav.put(serial.getTitle(), 0);
            }
        }
        // aflam numarul de aparitii din listele de favorite pentru fiecare video
        for (UserInputData user: input.getUsers()
             ) {
            for (String fav: user.getFavoriteMovies()
                 ) {
                if (listaFav.containsKey(fav)) {
                    listaFav.put(fav, listaFav.get(fav) + 1);
                }
            }
        }

        ArrayList<Map.Entry<String, Integer>> listaFin = new ArrayList<>();
        for (Map.Entry<String, Integer> intrare: listaFav.entrySet()
        ) {
            if (intrare.getValue() != 0) {
                listaFin.add(intrare);
            }
        }

        // sortam lista de titluri conform cerintei
        StringBuilder result = new StringBuilder("FavoriteRecommendation result: ");
            listaFin.sort(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(final Map.Entry<String, Integer> o1,
                                   final Map.Entry<String, Integer> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });

            result.append(listaFin.get(0).getKey());
            String finalResult = result.toString();
            // afisam rezultatul
            arrayResult.add(fileWriter.writeFile(inputData.getActionId(),
                    "message", finalResult));
        }


}
