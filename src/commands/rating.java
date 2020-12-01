package commands;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

public class Rating {
    /**
    * metoda voteaza este folosita pentru ca utilizatorul sa poata da un
     * rating unui video
     */
    public void voteaza(final Input input, final ActionInputData inputData,
                        final JSONArray arrayResult, final Writer fileWriter)
            throws java.io.IOException {
        for (UserInputData userData : input.getUsers()
        ) {  // cautam utilizatorl nostru
            if (userData.getUsername().compareTo(inputData.getUsername()) == 0) {
                // vedem daca a vizionat videoul
                if (userData.getHistory().containsKey(inputData.getTitle())) {
                    if (inputData.getSeasonNumber() == 0) {
                        // cazul in care este vorba de un film
                        Float grade = (float) inputData.getGrade();
                        if (userData.getRatedMovies() == null) {
                            userData.makeRatedMovies();
                        }
                        userData.rateMovie(inputData.getTitle(), grade);
                    } else {
                        // cazul in care este vorba de un serial
                        Float grade = (float) inputData.getGrade();
                        if (userData.getRatedShows() == null) {
                            userData.makeRatedShows();
                        }
                        userData.rateShow(inputData.getTitle(), grade);
                    }
                    // cazul in care videoclipul este vazut
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(),
                            "message", "success -> " + inputData.getTitle()
                                    + " was rated with " + inputData.getGrade()
                                    + " by " + inputData.getUsername()));
                } else {
                    // cazul in care videoclipul inca nu a fost vizionat
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(),
                            "message", "error -> " + inputData.getTitle()
                                    + " is not seen"));
                }
            }
        }
    }
}
