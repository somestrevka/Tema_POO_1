package commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

public class View {
    /**
    * mai jos se regaseste functia de vizionare
     * adauga materialul vizionat la istoricul utilizatorului
    */
    public void watch(final Input input, final ActionInputData inputData,
                      final JSONArray arrayResult, final Writer fileWriter)
            throws java.io.IOException {
        for (UserInputData userData: input.getUsers()
        ) {  // cautam utilizatorul
            if (userData.getUsername().compareTo(inputData.getUsername()) == 0) {
                // daca a vazut deja videoul, adaugam 1 la numarul de vizionari
                if (userData.getHistory().containsKey(inputData.getTitle())) {
                    int number = userData.getHistory().get(inputData.getTitle());
                    number++;
                    userData.getHistory().put(inputData.getTitle(), number);
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(),
                            "message", "success -> " + inputData.getTitle()
                                    + " was viewed with total views of " + number));
                } else {
                    // daca nu a vazut videoul, il adaugam la lista de vizionate
                    userData.getHistory().put(inputData.getTitle(), 1);
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(),
                            "message", "success -> " + inputData.getTitle()
                                    + " was viewed with total views of 1"));
                }
            }

        }
    }
}
