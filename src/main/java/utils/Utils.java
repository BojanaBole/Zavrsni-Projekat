package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.PostResponse;


import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    public static void createJsonFile (String name, Object o) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(Constants.PROJECT_ROOT + "/src/test/resources/" + name + ".json"), o);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object readFromJson(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(Paths.get(path).toFile(), Object.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


