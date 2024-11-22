package org.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.entities.Animal;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class YamlConfigLoader {

    public static Map<String, Map<String, Integer>> loadEatProbabilities(String path){
        ObjectMapper yamlLoader = new ObjectMapper(new YAMLFactory());
        ClassLoader configLoader = YamlConfigLoader.class.getClassLoader();
        try(InputStream stream = configLoader.getResourceAsStream(path)) {
            return yamlLoader.readValue(stream, new TypeReference<Map<String, Map<String, Integer>>>() {});
        }
        catch (Exception e){
            e.printStackTrace();
            return Collections.emptyMap();
        }

    }

    public static void loadAnimalFieldsConfig(Animal animal){
        if (animal.getClass().isAnnotationPresent(Config.class)){
            Config annotation = animal.getClass().getAnnotation(Config.class);
            String path = annotation.value();
            ObjectMapper loadConfig = new ObjectMapper(new YAMLFactory());
            ClassLoader classLoader = YamlConfigLoader.class.getClassLoader();
            try(InputStream inputStream = classLoader.getResourceAsStream(path)) {
                Map<?, ?> map = loadConfig.readValue(inputStream, Map.class);
                Field[] fields = Animal.class.getDeclaredFields();
                for (Field field : fields) {
                    if (map.containsKey(field.getName())){
                        field.setAccessible(true);
                        field.set(animal, map.get(field.getName()));
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
