package main;

import checker.Checkstyle;
import checker.Checker;
import commands.command;
import common.Constants;
import fileio.*;
import org.json.simple.JSONArray;
import query.query;
import user.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        InputLoader inputLoader = new InputLoader(filePath1);
        Input input = inputLoader.readData();

        Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        //TODO add here the entry point to your implementation

        for (ActionInputData inputData: input.getCommands()
             ) {
            if (inputData.getActionType().compareTo("recommendation") == 0) {
                for (UserInputData userData: input.getUsers()
                     ) {
                    if (userData.getUsername().compareTo(inputData.getUsername()) == 0) {
                        for (MovieInputData movieInput: input.getMovies()
                             ) {
                            if (!userData.getHistory().containsKey(movieInput.getTitle())) {
                                arrayResult.add(fileWriter.writeFile(inputData.getActionId(), "message","StandardRecommendation result: " + movieInput.getTitle()));
                                break;
                            }
                        }
                    }
                }
            }
            if (inputData.getActionType().compareTo("command") == 0) {
                command comanda = new command(input);
                comanda.rulare(inputData, arrayResult, fileWriter);
            }
            if (inputData.getActionType().compareTo("query") == 0) {
                query query = new query(input);
                query.vedem_ce(inputData, arrayResult, fileWriter);
            }
        }

        fileWriter.closeJSON(arrayResult);
    }
}
