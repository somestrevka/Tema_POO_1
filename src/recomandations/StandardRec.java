package recomandations;


import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import fileio.Writer;
import fileio.MovieInputData;
import org.json.simple.JSONArray;

public class StandardRec {
    /**
    * recomandare standard
     */
    public void standardRec(final Input input, final ActionInputData inputData,
                            final JSONArray arrayResult, final Writer fileWriter)
            throws java.io.IOException {
        // intoarce primul videoclip nevazut de catre utilizator
        for (
                UserInputData userData : input.getUsers()
        ) {
            if (userData.getUsername().compareTo(inputData.getUsername()) == 0) {
                // gasim utilizatorul nostru
                for (MovieInputData movieInput : input.getMovies()
                ) {
                    // gasim primul videoclip nevizionat
                    if (!userData.getHistory().containsKey(movieInput.getTitle())) {
                        arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                                "StandardRecommendation result: " + movieInput.getTitle()));
                        break;
                    }
                }
            }
        }
    }
}
