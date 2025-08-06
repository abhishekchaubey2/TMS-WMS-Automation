package com.delhivery.core.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;

import org.testng.SkipException;
import org.yaml.snakeyaml.Yaml;

@SuppressWarnings("unchecked")
public class YamlReader {

    private final static String yamlFilePath = System.getProperty("user.dir") + "/src/main/resources/staging_cmu.yml";

    public static Map<String, Object> getYamlValues(String token) {
        Reader doc;
        Yaml yaml;
        Map<String, Object> object;
        try {
            doc = new FileReader(yamlFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("YAML file not found at " + yamlFilePath);
        }
        yaml = new Yaml();
        object = yaml.load(doc);
        return pareYMLValues(object, token + ".");
    }

    // Parse the YML file and return the value of the token
    private static Map<String, Object> pareYMLValues(Map<String, Object> object, String token) {
        if (object == null) {
            System.out.println("Error: The YML data structure provided is null");
            throw new SkipException(
                    "Terminating process due to Error: The YML data structure provided is null");
        }

        if (token.contains(".")) {
            String[] st = token.split("\\.");
            Object nextObject = object.get(st[0]);

            // check if nextObject is a map
            if (nextObject instanceof Map<?, ?>) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) nextObject;
                object = pareYMLValues(map, token.replace(st[0] + ".", ""));
            } else {
                // if nextObject is not a map, then it is a leaf node
                System.out.println(
                        "Error: The key '" + st[0] + "' was not found in the YML data structure");
                throw new SkipException(
                        "Error: The key '" + st[0] + "' was not found in the YML data structure");
            }
        }
        System.out.println("Values from YML " + object);
        return object;
    }
}
