package ru.lian.ky.advent.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class OneLetterDiffApp {

    private static final Logger logger = LoggerFactory.getLogger(OneLetterDiffApp.class);
    private static final URL INPUT_FILE_URL = OneLetterDiffApp.class.getClassLoader().getResource("input.txt");


    public static void main(String[] args) {
        try {
            List<String> inputStrings = getInputStrings();
            
        } catch (URISyntaxException | IOException e) {
            logger.error("One letter diff error: {}", e);
        }
    }

    private static List<String> getInputStrings() throws URISyntaxException, IOException {
        Path path = Paths.get(Objects.requireNonNull(INPUT_FILE_URL).toURI());
        return Files.readAllLines(path);
    }

}
