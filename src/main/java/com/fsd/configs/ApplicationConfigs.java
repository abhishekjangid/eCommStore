package com.fsd.configs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public enum ApplicationConfigs {

    INSTANCE;

    ApplicationConfigs(){
        // TODO write same code using classic java File reader
        try {
            Path path = Paths.get(getClass().getClassLoader()
                    .getResource("application.properties").toURI());
            configs =  Files.lines(path)
                    .map(line -> line.split("=", 2))
                    .collect(Collectors.toMap(e -> e[0], e -> e[1]));
        }catch(IOException | URISyntaxException e) {
            throw new RuntimeException("!!Exception while reading application config", e);
        }
    }

    public static ApplicationConfigs getInstance(){
        return INSTANCE;
    }

    private Map<String, String> configs;

    public String get(String property) {
        return this.configs.get(property);
    }
}
