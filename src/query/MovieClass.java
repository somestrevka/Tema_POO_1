package query;

import fileio.ActionInputData;
import fileio.Input;
import fileio.MovieInputData;
import fileio.Writer;
import fileio.UserInputData;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;

public class MovieClass {
    /**
    * query-ul pentru filme
     */
    public void sortamFilme(final Input input, final ActionInputData inputData,
                            final JSONArray arrayResult, final Writer fileWriter)
            throws java.io.IOException {
        // indiferent de caz, vom cauta sa construim o lista cu filmele care
        // respecta criteriile, vom sorta lista si vom construi un mesaj de output
        if (inputData.getCriteria().compareTo("longest") == 0) {
            HashMap<String, Integer> filmeGasite = new HashMap<>();

            int count = 0;
            for (MovieInputData movieData: input.getMovies()
                 ) {
                int genuri = 1, avemAn = 1, an;
                List<List<String>> listaCriterii = inputData.getFilters();
                List<String> aux = listaCriterii.get(1);
                if (listaCriterii.get(0).get(0) != null) {
                    an = Integer.valueOf(listaCriterii.get(0).get(0));
                    if (an != movieData.getYear()) {
                        avemAn = 0;
                    }
                }
                for (int i = 0; i < aux.size(); i++) {
                    if (!movieData.getGenres().contains(aux.get(i))) {
                        genuri = 0;
                    }
                }
                if (avemAn == 1 && genuri == 1) {
                    filmeGasite.put(movieData.getTitle(), movieData.getDuration());
                    count++;
                }
            }
            ArrayList<Map.Entry<String, Integer>> listaFin = new ArrayList<>();
            for (Map.Entry<String, Integer> intrare: filmeGasite.entrySet()
                 ) {
                listaFin.add(intrare);
            }


            StringBuilder result = new StringBuilder("Query result: [");
            if (inputData.getSortType().compareTo("asc") == 0) {
                listaFin.sort(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(final Map.Entry<String, Integer> o1,
                                       final Map.Entry<String, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
                Boolean verif = false;
                for (Map.Entry<String, Integer> entry: listaFin
                ) {
                    verif = true;
                    result.append(entry.getKey()).append(", ");
                }
                if (verif == true) {
                    result.deleteCharAt(result.length() - 1);
                    result.deleteCharAt(result.length() - 1);
                }
           result.append("]");
                String finalResult = result.toString();
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                        finalResult));
            } else {
                listaFin.sort(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(final Map.Entry<String, Integer> o1,
                                       final Map.Entry<String, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
                Boolean verif = false;
                for (Map.Entry<String, Integer> entry: listaFin
                ) {
                    verif = true;
                    result.append(entry.getKey()).append(", ");
                }
                if (verif == true) {
                    result.deleteCharAt(result.length() - 1);
                    result.deleteCharAt(result.length() - 1);
                }
                result.append("]");
                String finalResult = result.toString();
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                        finalResult));
            }
        }
        if (inputData.getCriteria().compareTo("favorite") == 0) {
            HashMap<String, Integer> filmeGasite = new HashMap<>();

            int count = 0;
            for (MovieInputData movieData: input.getMovies()
            ) {
                int genuri = 1, avemAn = 1, an;
                List<List<String>> listaCriterii = inputData.getFilters();
                List<String> aux = listaCriterii.get(1);
                if (listaCriterii.get(0).get(0) != null) {
                    an = Integer.valueOf(listaCriterii.get(0).get(0));
                    if (an != movieData.getYear()) {
                        avemAn = 0;
                    }
                }
                for (int i = 0; i < aux.size(); i++) {
                    if (!movieData.getGenres().contains(aux.get(i))) {
                        genuri = 0;
                    }
                }
                if (avemAn == 1 && genuri == 1) {
                    filmeGasite.put(movieData.getTitle(), 0);
                    count++;
                }
            }
            for (UserInputData user: input.getUsers()
                 ) {
                for (String filme: user.getFavoriteMovies()
                     ) {
                    if (filmeGasite.containsKey(filme)) {
                        filmeGasite.put(filme, filmeGasite.get(filme) + 1);
                    }
                }
            }
            ArrayList<Map.Entry<String, Integer>> listaFin = new ArrayList<>();
            for (Map.Entry<String, Integer> intrare: filmeGasite.entrySet()
            ) {
                listaFin.add(intrare);
            }


            StringBuilder result = new StringBuilder("Query result: [");
            if (inputData.getSortType().compareTo("asc") == 0) {
                listaFin.sort(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(final Map.Entry<String, Integer> o1,
                                       final Map.Entry<String, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
                Boolean verif = false;
                for (Map.Entry<String, Integer> entry: listaFin
                ) {
                    verif = true;
                    result.append(entry.getKey()).append(", ");
                }
                if (verif == true) {
                    result.deleteCharAt(result.length() - 1);
                    result.deleteCharAt(result.length() - 1);
                }
                result.append("]");
                String finalResult = result.toString();
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                        finalResult));
            } else {
                listaFin.sort(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(final Map.Entry<String, Integer> o1,
                                       final Map.Entry<String, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
                Boolean verif = false;
                for (Map.Entry<String, Integer> entry: listaFin
                ) {
                    verif = true;
                    result.append(entry.getKey()).append(", ");
                }
                if (verif == true) {
                    result.deleteCharAt(result.length() - 1);
                    result.deleteCharAt(result.length() - 1);
                }
                result.append("]");
                String finalResult = result.toString();
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                        finalResult));
            }

        }
        if (inputData.getCriteria().compareTo("most_viewed") == 0) {
            HashMap<String, Integer> filmeGasite = new HashMap<>();

            int count = 0;
            for (MovieInputData movieData: input.getMovies()
            ) {
                int genuri = 1, avemAn = 1, an;
                List<List<String>> listaCriterii = inputData.getFilters();
                List<String> aux = listaCriterii.get(1);
                if (listaCriterii.get(0).get(0) != null) {
                    an = Integer.valueOf(listaCriterii.get(0).get(0));
                    if (an != movieData.getYear()) {
                        avemAn = 0;
                    }
                }
                for (int i = 0; i < aux.size(); i++) {
                    if (!movieData.getGenres().contains(aux.get(i))) {
                        genuri = 0;
                    }
                }
                if (avemAn == 1 && genuri == 1) {
                    filmeGasite.put(movieData.getTitle(), 0);
                    count++;
                }
            }
            for (UserInputData user: input.getUsers()
            ) {
                for (Map.Entry<String, Integer> filme: user.getHistory().entrySet()
                ) {
                    if (filmeGasite.containsKey(filme.getKey())) {
                        filmeGasite.put(filme.getKey(), filmeGasite.get(filme.getKey())
                                + filme.getValue());
                    }
                }
            }
            ArrayList<Map.Entry<String, Integer>> listaFin = new ArrayList<>();
            for (Map.Entry<String, Integer> intrare: filmeGasite.entrySet()
            ) {
                if (intrare.getValue() != 0) {
                    listaFin.add(intrare);
                }
            }


            StringBuilder result = new StringBuilder("Query result: [");
            if (inputData.getSortType().compareTo("asc") == 0) {
                listaFin.sort(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(final Map.Entry<String, Integer> o1,
                                       final Map.Entry<String, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
                Boolean verif = false;
                for (Map.Entry<String, Integer> entry: listaFin
                ) {
                    verif = true;
                    result.append(entry.getKey()).append(", ");
                }
                if (verif == true) {
                    result.deleteCharAt(result.length() - 1);
                    result.deleteCharAt(result.length() - 1);
                }
                result.append("]");
                String finalResult = result.toString();
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                        finalResult));
            } else {
                listaFin.sort(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(final Map.Entry<String, Integer> o1,
                                       final Map.Entry<String, Integer> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                Boolean verif = false;
                for (Map.Entry<String, Integer> entry: listaFin
                ) {
                    verif = true;
                    result.append(entry.getKey()).append(", ");
                }
                if (verif == true) {
                    result.deleteCharAt(result.length() - 1);
                    result.deleteCharAt(result.length() - 1);
                }
                result.append("]");
                String finalResult = result.toString();
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                        finalResult));
            }

        }
        // mai putin in cazul "ratings"
        if (inputData.getCriteria().compareTo("ratings") == 0) {
            RatingMovie ratMovie = new RatingMovie();
            ratMovie.ratingMovie(input, inputData, arrayResult, fileWriter);
        }
    }


}
