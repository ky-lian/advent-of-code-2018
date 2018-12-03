package ru.lian.ky.advent.inventory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LetterCountChecksumApp {

    private static final Logger logger = LoggerFactory.getLogger(LetterCountChecksumApp.class);
    private static final URL INPUT_FILE_URL = LetterCountChecksumApp.class.getClassLoader().getResource("input.txt");

    public static void main(String[] args) {

        try {
            Collection<String> strings = getInputStrings();
            logger.info("Hash: {}", hash(strings));
        } catch (URISyntaxException | IOException e) {
            logger.error("Exception occurred while reading input", e);
        }

    }

    private static List<String> getInputStrings() throws URISyntaxException, IOException {
        Path path = Paths.get(Objects.requireNonNull(INPUT_FILE_URL).toURI());
        return Files.readAllLines(path);
    }

    private static int hash(Collection<String> strings) {

        int twoCharacterCount = 0;
        int threeCharacterCount = 0;

        for (String string : strings) {
            Collection<Long> values = string.chars()
                    .boxed()
                    .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                    .values();

            if (values.contains(2L)) {
                twoCharacterCount++;
            }
            if (values.contains(3L)) {
                threeCharacterCount++;
            }

        }
        return twoCharacterCount * threeCharacterCount;
    }

}
