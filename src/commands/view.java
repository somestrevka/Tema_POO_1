package commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

public class view {

    public void watch(Input input, ActionInputData inputData, JSONArray arrayResult, Writer fileWriter) throws java.io.IOException {
        for (UserInputData userData: input.getUsers()
        ) {
            if (userData.getUsername().compareTo(inputData.getUsername()) == 0) {
                if (userData.getHistory().containsKey(inputData.getTitle())) {
                    int number = userData.getHistory().get(inputData.getTitle());
                    number++;
                    userData.getHistory().put(inputData.getTitle(), number);
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "success -> " + inputData.getTitle() + " was viewed with total views of " + number));
                } else {
                    userData.getHistory().put(inputData.getTitle(), 1);
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "success -> " + inputData.getTitle() + " was viewed with total views of 1"));
                }
            }

        }
    }
}
