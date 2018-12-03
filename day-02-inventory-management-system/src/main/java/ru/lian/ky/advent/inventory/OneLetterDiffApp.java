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

            String result = getString(getInputStrings());
            logger.info("Result is: {}", result.isEmpty() ? "error" : result);

        } catch (URISyntaxException | IOException e) {
            logger.error("One letter diff error: {}", e);
        }
    }

    private static String getString(List<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            for (int j = 0; j < strings.size(); j++) {
                if (i == j) {
                    continue;
                }

                int position = findSingleDiffCharacterPosition(strings.get(i), strings.get(j));
                if (position != -1) {
                    return new StringBuilder(strings.get(i))
                            .deleteCharAt(position)
                            .toString();
                }
            }
        }
        return "";
    }

    private static List<String> getInputStrings() throws URISyntaxException, IOException {
        Path path = Paths.get(Objects.requireNonNull(INPUT_FILE_URL).toURI());
        return Files.readAllLines(path);
    }

    private static int findSingleDiffCharacterPosition(String a, String b) {
        if (a.length() != b.length()) {
            return -1;
        }

        char[] aCharArray = a.toCharArray();
        char[] bCharArray = b.toCharArray();

        int firstDiffPosition = -1;
        for (int i = 0; i < aCharArray.length; i++) {
            char aChar = aCharArray[i];
            char bChar = bCharArray[i];

            if (aChar != bChar && firstDiffPosition == -1) {
                firstDiffPosition = i;
            } else if (aChar != bChar) {
                return -1;
            }
        }
        return firstDiffPosition;
    }

}
