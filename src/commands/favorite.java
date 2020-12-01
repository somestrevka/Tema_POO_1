package commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

public class favorite {
    public void adauga(Input input, ActionInputData inputData, JSONArray arrayResult, Writer fileWriter) throws java.io.IOException{
        for (UserInputData userData: input.getUsers()
        ) {
            if (userData.getUsername().compareTo(inputData.getUsername()) == 0) {
                if (!userData.getHistory().containsKey(inputData.getTitle())) {
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message","error -> " + inputData.getTitle() + " is not seen" ));
                } else {
                    if (userData.getFavoriteMovies().contains(inputData.getTitle())) {
                        arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "error -> " + inputData.getTitle() + " is already in favourite list"));
                    } else {
                        userData.adauga_fav(inputData.getTitle());
                        arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "success -> " + inputData.getTitle() + " was added as favourite"));
                    }
                }
            }

        }

    }
}
