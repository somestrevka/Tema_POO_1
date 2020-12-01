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

public class actors_class {
    public void sortam_actori(Input input, ActionInputData inputData, JSONArray arrayResult, Writer fileWriter) throws java.io.IOException{
        if (inputData.getCriteria().compareTo("awards") == 0) {
            int sum = 0, t = 1, totals = 0;
            HashMap<String, Integer> lista_actori = new HashMap<>();
            Map<ActorsAwards, Integer> aux;
            for (ActorInputData actori: input.getActors()
                 ) {
                t = 1;
                sum = 0;
                aux = actori.getAwards();
                List<List<String>> lista_criterii = inputData.getFilters();
                List<String> tlwalf; // the list we are looking for
                for (int i = 0; i < lista_criterii.size(); i++) { // cautam lista de premii
                    if (lista_criterii.get(i) != null) {
                        tlwalf = lista_criterii.get(i);
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
                    lista_actori.put(actori.getName(), sum);
                }


            }

            if (totals > 0) {
                //arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", ))
            } else {
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "Query result: []"));
            }
        }
        if (inputData.getCriteria().compareTo("filter_description") == 0) {
            int t = 1; // va avea 1 daca actorul are toate cuvintele in descriere si 0 in caz contrar
            int n_actors = 0;
            HashMap<String, Integer> lista_actori = new HashMap<>();
            List<String> aux;
            List<List<String>> lista_criterii = inputData.getFilters();
            List<String> tcwalf; // the criterias we are looking for
            ArrayList<String> actori_gasiti = new ArrayList<String>();

            for (ActorInputData actorul: input.getActors()
                 ) {
                for (int i = 0; i < lista_criterii.size(); i++) {
                    if (lista_criterii.get(i) != null) {
                        tcwalf = lista_criterii.get(i);
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
                    n_actors++;
                    actori_gasiti.add(actorul.getName());
                }

            }
            if (n_actors == 0) {
                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "Query result: []"));
            }
        }
    }
}
