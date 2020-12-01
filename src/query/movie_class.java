package query;

import fileio.ActionInputData;
import fileio.Input;
import fileio.MovieInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.util.*;
import java.util.stream.Collectors;

public class movie_class {

    public void sortam_filme(Input input, ActionInputData inputData, JSONArray arrayResult, Writer fileWriter) throws java.io.IOException{
        if (inputData.getCriteria().compareTo("longest") == 0) {
            HashMap<Integer, String> filme_gasite = null;
            int[] dim_gas = new int[10];
            int count = 0;
            for (MovieInputData movieData: input.getMovies()
                 ) {
                int genuri = 1, avem_an = 1;
                List<List<String>> lista_criterii = inputData.getFilters();
                List<String> aux;
                for (int i = 0; i < lista_criterii.size(); i++) {
                    if (lista_criterii.get(i) != null) {
                        aux = lista_criterii.get(i);
                        for (int j = 0; j < aux.size(); j++) {
                            if (!movieData.getGenres().contains(aux.get(j))) {
                                genuri = 0;
                            }
                        }
                        String anul = String.valueOf(movieData.getYear());
                        if (!aux.contains(anul)) {
                            avem_an = 0;
                        }
                    }
                }
                if (avem_an == 1 && genuri == 1) {
                    filme_gasite.put(movieData.getDuration() ,movieData.getTitle());
                    dim_gas[count] = movieData.getDuration();
                    count++;
                }
            }
            StringBuilder result = new StringBuilder("Query result: ");
            if (inputData.getSortType().compareTo("asc") == 0) {
                for (int i = 0; i < dim_gas.length - 1; i++) {
                    for (int j = i + 1; j < dim_gas.length; j++) {
                        if (dim_gas[i] > dim_gas[j]) {
                            int aux1 = dim_gas[i];
                            dim_gas[i] = dim_gas[j];
                            dim_gas[j] = aux1;
                        }
                    }
                }
                for (int i = 0; i < dim_gas.length; i++) {
                    result.append(filme_gasite.get(dim_gas[i]));
                    if (i < dim_gas.length - 1) {
                        result.append(", ");
                    }
                }
                result.append("]");
                String final_result = result.toString();
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", final_result));
            }
        }
    }


}
