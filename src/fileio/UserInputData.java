package fileio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Information about an user, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class UserInputData {
    /**
     * User's username
     */
    private final String username;
    /**
     * Subscription Type
     */
    private final String subscriptionType;
    /**
     * The history of the movies seen
     */
    private final Map<String, Integer> history;
    /**
     * Movies added to favorites
     */
    private final ArrayList<String> favoriteMovies;
    /**
    * filme recenziate
     */
    private Map<String, Float> ratedMovies;
    /**
    * getterul pentru ratedMovies
     */

    public Map<String, Float> getRatedMovies() {
        return ratedMovies;
    }
    /**
    * initializarea lui ratedMovies
     */

    public void makeRatedMovies() {
        this.ratedMovies = new HashMap<>();
    }
    /**
    * initializarea lui ratedShows
     */

    public void makeRatedShows() {
        this.ratedShows = new HashMap<>();
    }
    /**
    * initializarea lui ratedShows
     */

    public Map<String, Float> getRatedShows() {
        return ratedShows;
    }

    /**
    * seriale recenziate
     */
    private Map<String, Float> ratedShows;
    /**
    * functie pentru recenzia filmelor
     */
    public void rateMovie(final String name, final Float grade) {
        this.ratedMovies.put(name, grade);
    }
    /**
    * functie pentru recenzia serialelor
     */
    public void rateShow(final String name, final Float grade) {
        this.ratedShows.replace(name, grade);
    }
    /**
    * functie care adauga un video la lista de favorite
     * a unui utilizator
     */
    public void adaugaFav(final String name) {
        this.favoriteMovies.add(name);
    }

    public UserInputData(final String username, final String subscriptionType,
                         final Map<String, Integer> history,
                         final ArrayList<String> favoriteMovies) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.favoriteMovies = favoriteMovies;
        this.history = history;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    @Override
    public String toString() {
        return "UserInputData{" + "username='"
                + username + '\'' + ", subscriptionType='"
                + subscriptionType + '\'' + ", history="
                + history + ", favoriteMovies="
                + favoriteMovies + '}';
    }
}
