package ru.lian.ky.advent.calibration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class Calibrator {

    private static final Logger logger = LoggerFactory.getLogger(Calibrator.class);
    private static final URL INPUT_FILE_URL = Calibrator.class.getClassLoader().getResource("input.txt");

    public static void main(String[] args) {

        try {
            int sum = getInputLines()
                    .mapToInt(Integer::valueOf)
                    .peek(value -> logger.debug("Read value: {}", value))
                    .sum();

            logger.info("Calibration result: {}", sum);

        } catch (URISyntaxException | IOException e) {
            logger.debug("Error while reading the file: {}", "input.txt", e);
        }
    }

    private static Stream<String> getInputLines() throws IOException, URISyntaxException {
        return Files.lines(Paths.get(Objects.requireNonNull(INPUT_FILE_URL).toURI()));
    }

}
