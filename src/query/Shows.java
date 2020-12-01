package query;

import Video.Serial;
import fileio.*;
import org.json.simple.JSONArray;

import java.util.*;

public class Shows {

    public void sortam_filme(Input input, ActionInputData inputData, JSONArray arrayResult, Writer fileWriter) throws java.io.IOException{
        if (inputData.getCriteria().compareTo("longest") == 0) {
            HashMap<String, Integer> seriale_gasite = new HashMap<>();

            int count = 0;
            for (SerialInputData serialData : input.getSerials()
            ) {
                int genuri = 1, avem_an = 1, an;
                List<List<String>> lista_criterii = inputData.getFilters();
                List<String> aux = lista_criterii.get(1);
                if (lista_criterii.get(0).get(0) != null) {
                    an = Integer.valueOf(lista_criterii.get(0).get(0));
                    if (an != serialData.getYear()) {
                        avem_an = 0;
                    }
                }
                for (int i = 0; i < aux.size(); i++) {
                    if(!serialData.getGenres().contains(aux.get(i))) {
                        genuri = 0;
                    }
                }
                if (avem_an == 1 && genuri == 1) {
                    seriale_gasite.put(serialData.getTitle(), serialData.getSeasons().get(0).getDuration() * serialData.getNumberSeason());
                    count++;
                }
            }
            ArrayList<Map.Entry<String, Integer>> lista_fin = new ArrayList<>();
            for (Map.Entry<String, Integer>intrare: seriale_gasite.entrySet()
            ) {
                lista_fin.add(intrare);
            }


            StringBuilder result = new StringBuilder("Query result: [");
            if (inputData.getSortType().compareTo("asc") == 0) {
                lista_fin.sort(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
                Boolean verif = false;
                for (Map.Entry<String, Integer> entry: lista_fin
                ) {
                    verif = true;
                    result.append(entry.getKey()).append(", ");
                }
                if (verif == true) {
                    result.deleteCharAt(result.length() - 1);
                    result.deleteCharAt(result.length() - 1);
                }
                result.append("]");
                String final_result = result.toString();
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", final_result));
            } else {
                lista_fin.sort(new Comparator<Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
                Boolean verif = false;
                for (Map.Entry<String, Integer> entry: lista_fin
                ) {
                    verif = true;
                    result.append(entry.getKey()).append(", ");
                }
                if (verif == true) {
                    result.deleteCharAt(result.length() - 1);
                    result.deleteCharAt(result.length() - 1);
                }
                result.append("]");
                String final_result = result.toString();
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", final_result));
            }
        }
    }
}
