package query;


import fileio.ActionInputData;
import fileio.Input;
import fileio.SerialInputData;
import fileio.Writer;
import fileio.UserInputData;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Comparator;


public class Shows {
    /**
    * query pentru seriale
     */
    public void sortamSeriale(final Input input, final ActionInputData inputData,
                               final JSONArray arrayResult, final Writer fileWriter)
            throws java.io.IOException {
        if (inputData.getCriteria().compareTo("longest") == 0) {
            // cautam serialele care respecta cerinta si le sortam, conform inputului
            HashMap<String, Integer> serialeGasite = new HashMap<>();

            int count = 0;
            for (SerialInputData serialData : input.getSerials()
            ) {
                int genuri = 1, avemAn = 1, an;
                List<List<String>> listaCriterii = inputData.getFilters();
                List<String> aux = listaCriterii.get(1);
                if (listaCriterii.get(0).get(0) != null) {
                    an = Integer.valueOf(listaCriterii.get(0).get(0));
                    if (an != serialData.getYear()) {
                        avemAn = 0;
                    }
                }
                for (int i = 0; i < aux.size(); i++) {
                    if (!serialData.getGenres().contains(aux.get(i))) {
                        genuri = 0;
                    }
                }
                if (avemAn == 1 && genuri == 1) {  // daca serialul la care ne uitam
                    // are atat anul, cat si genurile cerute, il adaugam in lista
                    serialeGasite.put(serialData.getTitle(),
                            serialData.getSeasons().get(0).getDuration()
                                    * serialData.getNumberSeason());
                    count++;
                }
            }
            ArrayList<Map.Entry<String, Integer>> listaFin = new ArrayList<>();
            for (Map.Entry<String, Integer> intrare: serialeGasite.entrySet()
            ) {
                listaFin.add(intrare);
            }

            // sortam lista dupa caz
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
                // construim mesajul de output
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
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(),
                        "message", finalResult));
            }
        }
        if (inputData.getCriteria().compareTo("favorite") == 0) {
            HashMap<String, Integer> serialeGasite = new HashMap<>();

            int count = 0;
            for (SerialInputData serialData : input.getSerials()
            ) {  // cautam serialele care se incadreaza in cerinta
                int genuri = 1, avemAn = 1, an;
                List<List<String>> listaCriterii = inputData.getFilters();
                List<String> aux = listaCriterii.get(1);
                if (listaCriterii.get(0).get(0) != null) {
                    an = Integer.valueOf(listaCriterii.get(0).get(0));
                    if (an != serialData.getYear()) {
                        avemAn = 0;
                    }
                }
                for (int i = 0; i < aux.size(); i++) {
                    if (!serialData.getGenres().contains(aux.get(i))) {
                        genuri = 0;
                    }
                }
                if (avemAn == 1 && genuri == 1) {
                    serialeGasite.put(serialData.getTitle(), 0);
                    count++;
                }
            }
            for (UserInputData user: input.getUsers()
            ) {
                for (String filme: user.getFavoriteMovies()
                ) {
                    if (serialeGasite.containsKey(filme)) {
                        serialeGasite.put(filme, serialeGasite.get(filme) + 1);
                    }
                }
            }
            ArrayList<Map.Entry<String, Integer>> listaFin = new ArrayList<>();
            for (Map.Entry<String, Integer> intrare: serialeGasite.entrySet()
            ) {
                if (intrare.getValue() != 0) {
                    listaFin.add(intrare);
                }
            }

            // sortam lista de seriale dupa caz
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
                // construim mesajul de output
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
            HashMap<String, Integer> serialeGasite = new HashMap<>();

            int count = 0;
            for (SerialInputData serialData : input.getSerials()
            ) {  // cautam serialele care se incadreaza in cerinta
                int genuri = 1, avemAn = 1, an;
                List<List<String>> listaCriterii = inputData.getFilters();
                List<String> aux = listaCriterii.get(1);
                if (listaCriterii.get(0).get(0) != null) {
                    an = Integer.valueOf(listaCriterii.get(0).get(0));
                    if (an != serialData.getYear()) {
                        avemAn = 0;
                    }
                }
                for (int i = 0; i < aux.size(); i++) {
                    if (!serialData.getGenres().contains(aux.get(i))) {
                        genuri = 0;
                    }
                }
                if (avemAn == 1 && genuri == 1) {
                    serialeGasite.put(serialData.getTitle(), 0);
                    count++;
                }
            }
            for (UserInputData user: input.getUsers()
            ) {
                for (Map.Entry<String, Integer> filme: user.getHistory().entrySet()
                ) {
                    if (serialeGasite.containsKey(filme.getKey())) {
                        serialeGasite.put(filme.getKey(), serialeGasite.get(filme.getKey())
                                + filme.getValue());
                    }
                }
            }
            ArrayList<Map.Entry<String, Integer>> listaFin = new ArrayList<>();
            for (Map.Entry<String, Integer> intrare: serialeGasite.entrySet()
            ) {
                if (intrare.getValue() != 0) {
                    listaFin.add(intrare);
                }
            }

            // le sortam dupa caz
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
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(),
                        "message", finalResult));
            } else {
                listaFin.sort(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(final Map.Entry<String, Integer> o1,
                                       final Map.Entry<String, Integer> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });
                Boolean verif = false;
                // generam sirul de output
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
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(),
                        "message", finalResult));
            }

        }
        if (inputData.getCriteria().compareTo("ratings") == 0) {
            RatingShow ratingShow = new RatingShow();
            ratingShow.ratingShow(input, inputData, arrayResult, fileWriter);
        }
    }
}
