package commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

public class Favorite {
    /**
    * adauga este folosita pentru a aduaga un viedeo la lista de favorite
     * a utilizatorului
     */
    public void adauga(final Input input, final ActionInputData inputData,
                        final JSONArray arrayResult, final Writer fileWriter)
            throws java.io.IOException {
        for (UserInputData userData: input.getUsers()
        ) {  //cuatam utilizatorul nostru
            if (userData.getUsername().compareTo(inputData.getUsername()) == 0) {
                if (!userData.getHistory().containsKey(inputData.getTitle())) {
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(),
                            "message", "error -> " + inputData.getTitle() + " is not seen"));
                    // cazul in care videoul inca nu a fost vizionat
                } else {
                    if (userData.getFavoriteMovies().contains(inputData.getTitle())) {
                        arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                                "error -> " + inputData.getTitle()
                                        + " is already in favourite list"));
                        // cazul in care este deja in lista de favorite
                    } else {
                        userData.adaugaFav(inputData.getTitle());
                        arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                                "success -> " + inputData.getTitle()
                                        + " was added as favourite"));
                    }
                }
            }

        }

    }
}
