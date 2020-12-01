package commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

public class command {
    Input input;
    public command(Input input) {
        this.input = input;
    }

    public void rulare(ActionInputData inputData, JSONArray arrayResult, Writer fileWriter) throws java.io.IOException{
        if (inputData.getType().compareTo("rating") == 0) {
            rating evaluare = new rating();
            evaluare.voteaza(this.input, inputData, arrayResult, fileWriter);
        }
        if (inputData.getType().compareTo("view") == 0) {
            view vizionare = new view();
            vizionare.watch(this.input, inputData, arrayResult, fileWriter);
        }
        if (inputData.getType().compareTo("favorite") == 0) {
            favorite favorit = new favorite();
            favorit.adauga(this.input, inputData, arrayResult, fileWriter);
        }
    }
}
