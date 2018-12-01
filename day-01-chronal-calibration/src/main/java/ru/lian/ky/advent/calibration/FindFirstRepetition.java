package ru.lian.ky.advent.calibration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class FindFirstRepetition {

    private static final Logger logger = LoggerFactory.getLogger(FindFirstRepetition.class);

    private static final URL INPUT_FILE_URL = FindFirstRepetition.class.getClassLoader().getResource("input.txt");
    private static final int SUM_INIT_VALUE = 0;

    public static void main(String[] args) {
        try {
            Collection<Integer> values = getInputValues();
            Integer firstDuplicate = findFirstDuplicateSum(values);

            logger.info("First repeat value: {}", firstDuplicate);
        } catch (IOException | URISyntaxException e) {
            logger.error("Error while trying to read the file: {}", "input.txt", e);
        }
    }

    private static Collection<Integer> getInputValues() throws IOException, URISyntaxException {
        Path path = Paths.get(Objects.requireNonNull(INPUT_FILE_URL).toURI());

        return Files.lines(path)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private static Integer findFirstDuplicateSum(Collection<Integer> values) {
        Integer sum = SUM_INIT_VALUE;

        Set<Integer> seen = new HashSet<>(8192);
        seen.add(sum);

        boolean duplicateNotFound = true;

        Iterator<Integer> iterator = values.iterator();
        while (duplicateNotFound) {
            if (!iterator.hasNext()) {
                iterator = values.iterator();
            }
            sum += iterator.next();
            duplicateNotFound = seen.add(sum);
        }
        return sum;
    }

}
