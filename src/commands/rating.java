package commands;

import fileio.Input;
import fileio.UserInputData;
import checker.Checkstyle;
import checker.Checker;
import commands.command;
import common.Constants;
import fileio.*;
import org.json.simple.JSONArray;
import user.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class rating {

    public void voteaza (Input input, ActionInputData inputData, JSONArray arrayResult, Writer fileWriter) throws java.io.IOException{
        for (UserInputData userData: input.getUsers()
        ) {
            if (userData.getUsername().compareTo(inputData.getUsername()) == 0) {
                if (userData.getHistory().containsKey(inputData.getTitle())) {
                    if (inputData.getSeasonNumber() == 0) {
                        Float grade = (float) inputData.getGrade();
                        if (userData.rated_movies == null) {
                            userData.rated_movies = new HashMap();
                        }
                        userData.rate_movie(inputData.getTitle(), grade);
                    } else {
                        Float grade = (float) inputData.getGrade();
                        if (userData.rated_shows == null) {
                            userData.rated_shows = new HashMap();
                        }
                        userData.rate_show(inputData.getTitle(), grade, inputData.getSeasonNumber());
                    }
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "success -> " + inputData.getTitle() + " was rated with " + inputData.getGrade() + " by " + inputData.getUsername()));
                } else {
                    arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message", "error -> " + inputData.getTitle() + " is not seen"));
                }
            }
        }
    }
}
