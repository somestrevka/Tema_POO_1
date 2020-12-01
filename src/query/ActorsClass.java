package query;

import actor.ActorsAwards;
import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.Input;
import fileio.Writer;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActorsClass {
    /**
    * query-ul pentru actori
     */
    public void sortamActori(final Input input, final ActionInputData inputData,
                             final JSONArray arrayResult, final Writer fileWriter)
            throws java.io.IOException {
        if (inputData.getCriteria().compareTo("awards") == 0) {
            int sum = 0, t = 1, totals = 0;
            HashMap<String, Integer> listaActori = new HashMap<>();
            Map<ActorsAwards, Integer> aux;
            for (ActorInputData actori: input.getActors()
                 ) {
                t = 1;
                sum = 0;
                aux = actori.getAwards();
                List<List<String>> listaCriterii = inputData.getFilters();
                List<String> tlwalf; // the list we are looking for
                for (int i = 0; i < listaCriterii.size(); i++) { // cautam lista de premii
                    if (listaCriterii.get(i) != null) {
                        tlwalf = listaCriterii.get(i);
                        for (int j = 0; j < tlwalf.size(); j++) {
                            if (aux.containsKey(tlwalf.get(j))) {
                                sum = sum + aux.get(tlwalf.get(i));
                            } else {
                                t = 0;
                            }
                        }
                    }
                }
                if (t == 1) {
                    totals = totals + sum;
                    listaActori.put(actori.getName(), sum);
                }
            }

            if (totals == 0) {
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                        "Query result: []"));
            }
        }
        if (inputData.getCriteria().compareTo("filter_description") == 0) {
            int t = 1; // va avea 1 daca actorul are toate cuvintele in descriere si 0
            int nActors = 0;  // in caz contrar
            HashMap<String, Integer> listaActori = new HashMap<>();
            List<String> aux;
            List<List<String>> listaCriterii = inputData.getFilters();
            List<String> tcwalf; // the criterias we are looking for
            ArrayList<String> actoriGasiti = new ArrayList<String>();

            for (ActorInputData actorul: input.getActors()
                 ) {  // verificam toti actorii
                for (int i = 0; i < listaCriterii.size(); i++) {
                    if (listaCriterii.get(i) != null) {
                        tcwalf = listaCriterii.get(i);
                        t = 1;
                        String description = actorul.getCareerDescription();
                        for (int j = 0; j < tcwalf.size(); j++) {
                            if (tcwalf.get(j) == null) {
                                break;
                            }
                            if (!description.contains(tcwalf.get(j))) {
                                t = 0;
                            }
                        }
                    }
                }
                if (t == 1) {
                    // daca actorul respecta criteriile, il adaugam la lista
                    nActors++;
                    actoriGasiti.add(actorul.getName());
                }

            }
            if (nActors == 0) {
                // cazul in care nu am gasit actori
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                        "Query result: []"));
            } else {
                // construim mesajul de output si il afisam
                StringBuilder result = new StringBuilder();
                result.append("Query result: [ ");
                for (int i = 0; i < actoriGasiti.size(); i++) {
                    result.append(actoriGasiti.get(i));
                    if (i < actoriGasiti.size() - 1) {
                        result.append(", ");
                    } else {
                        result.append("]");
                    }
                }
                String finalResult = result.toString();
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                        finalResult));
            }
        }
    }
}
