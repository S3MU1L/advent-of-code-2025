package io.xmalec;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Utils {

    public static Stream<String> readFile(String fileName) {
        try {
            return Files.lines(Path.of(Utils.class.getResource("/" + fileName).toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
