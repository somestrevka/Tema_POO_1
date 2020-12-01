package recomandations;

import fileio.ActionInputData;
import fileio.Input;
import fileio.UserInputData;
import fileio.Writer;
import org.json.simple.JSONArray;

public class Recomandation {
    private Input input;
    public Recomandation(final Input input) {
        this.input = input;
    }
    /**
     * tratam recomandarile si apelam in continuare functia necesara
     * sau afisam mesaj de eroare daca un user BASIC apeleaza o functionalitate premium
     */
    public void recomandam(final ActionInputData inputData, final JSONArray arrayResult,
                           final Writer fileWriter) throws java.io.IOException {
        // vedem ce tip de recomandare se cere
        if (inputData.getType().compareTo("favorite") == 0) {
            for (UserInputData user: this.input.getUsers()
                 ) {
                // verificam daca utilizatorul este premium sau basic
                if (user.getUsername().compareTo(inputData.getUsername()) == 0) {
                    if (user.getSubscriptionType().compareTo("BASIC") == 0) {
                        arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                                "FavoriteRecommendation cannot be applied!"));
                        break;
                    } else {
                        FavRec fav = new FavRec();
                        fav.recomandFav(this.input, inputData, arrayResult, fileWriter);
                    }
                }
            }

        }
        if (inputData.getType().compareTo("standard") == 0) {
            StandardRec standRec = new StandardRec();
            standRec.standardRec(this.input, inputData, arrayResult, fileWriter);
        }
        if (inputData.getType().compareTo("popular") == 0) {
            for (UserInputData user: this.input.getUsers()
            ) {
                if (user.getUsername().compareTo(inputData.getUsername()) == 0) {
                    if (user.getSubscriptionType().compareTo("BASIC") == 0) {
                        arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                                "PopularRecommendation cannot be applied!"));
                        break;
                    } else {
                        Popular popular = new Popular();
                        popular.popRec(this.input, inputData, arrayResult, fileWriter);
                    }
                }
            }

        }
        if (inputData.getType().compareTo("search") == 0) {
            for (UserInputData user: this.input.getUsers()
            ) {
                if (user.getUsername().compareTo(inputData.getUsername()) == 0) {
                    if (user.getSubscriptionType().compareTo("BASIC") == 0) {
                        arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message",
                                "SearchRecommendation cannot be applied!"));
                        break;
                    } else {
                        Search search = new Search();
                        search.cautam(this.input, inputData, arrayResult, fileWriter);
                    }
                }
            }

        }

    }
}
