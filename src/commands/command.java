package commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.Writer;
import org.json.simple.JSONArray;

public class Command {
    private Input input;
    public Command(final Input input) {
        this.input = input;
    }

    /**
    * functia rulare este folosita pentru a stabili comanda
    */
    public void rulare(final ActionInputData inputData, final JSONArray arrayResult,
                       final Writer fileWriter) throws java.io.IOException {
        // stabilim tipul comenzii
        if (inputData.getType().compareTo("rating") == 0) {
            Rating evaluare = new Rating();
            evaluare.voteaza(this.input, inputData, arrayResult, fileWriter);
        }
        if (inputData.getType().compareTo("view") == 0) {
            View vizionare = new View();
            vizionare.watch(this.input, inputData, arrayResult, fileWriter);
        }
        if (inputData.getType().compareTo("favorite") == 0) {
            Favorite favorit = new Favorite();
            favorit.adauga(this.input, inputData, arrayResult, fileWriter);
        }
    }
}
