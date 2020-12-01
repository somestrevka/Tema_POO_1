package query;

import fileio.ActionInputData;
import fileio.Input;
import fileio.Writer;
import org.json.simple.JSONArray;

public class query {
    Input input;
    public query (Input input) {
        this.input = input;
    }

    public void vedem_ce(ActionInputData inputData, JSONArray arrayResult, Writer fileWriter) throws java.io.IOException{
        if (inputData.getObjectType().compareTo("actors") == 0) {
            actors_class actors_place = new actors_class();
            actors_place.sortam_actori(this.input, inputData, arrayResult, fileWriter);
        }
        if (inputData.getObjectType().compareTo("movies") == 0) {
            movie_class movie_spot = new movie_class();
            movie_spot.sortam_filme(this.input, inputData, arrayResult, fileWriter);
        }
        if (inputData.getObjectType().compareTo("shows") == 0) {
            movie_class movie_spot = new movie_class();
            movie_spot.sortam_filme(this.input, inputData, arrayResult, fileWriter);
        }

    }
}
