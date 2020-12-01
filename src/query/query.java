package query;

import fileio.ActionInputData;
import fileio.Input;
import fileio.Writer;
import org.json.simple.JSONArray;

public class Query {
    /**
    * aici stabilim ce query apelam
     */
    private Input input;
    public Query(final Input input) {
        this.input = input;
    }
    /**
    * gasim cu ce tip de query ne confruntam
     */
    public void vedemCe(final ActionInputData inputData, final JSONArray arrayResult,
                         final Writer fileWriter) throws java.io.IOException {
        if (inputData.getObjectType().compareTo("actors") == 0) {
            ActorsClass actorsPlace = new ActorsClass();
            actorsPlace.sortamActori(this.input, inputData, arrayResult, fileWriter);
        }
        if (inputData.getObjectType().compareTo("movies") == 0) {
            MovieClass movieSpot = new MovieClass();
            movieSpot.sortamFilme(this.input, inputData, arrayResult, fileWriter);
        }
        if (inputData.getObjectType().compareTo("shows") == 0) {
            Shows serialSpot = new Shows();
            serialSpot.sortamSeriale(this.input, inputData, arrayResult, fileWriter);
        }

    }
}
