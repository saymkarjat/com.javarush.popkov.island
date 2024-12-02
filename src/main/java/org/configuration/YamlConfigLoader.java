package org.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.entity.Animal;
import org.entity.herbivores.*;
import org.entity.plants.Grass;
import org.entity.predators.*;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class YamlConfigLoader {

    public static Map<Class<?>, Map<Class<?>, Integer>> getEatProbabilities() {
        Map<Class<?>, Map<Class<?>, Integer>> eatProbabilities = new HashMap<>();

        // Добавление для Wolf
        Map<Class<?>, Integer> wolfPrey = new HashMap<>();
        wolfPrey.put(Horse.class, 10);
        wolfPrey.put(Deer.class, 15);
        wolfPrey.put(Rabbit.class, 60);
        wolfPrey.put(Mouse.class, 80);
        wolfPrey.put(Goat.class, 60);
        wolfPrey.put(Sheep.class, 70);
        wolfPrey.put(Boar.class, 15);
        wolfPrey.put(Buffalo.class, 10);
        wolfPrey.put(Duck.class, 40);
        eatProbabilities.put(Wolf.class, wolfPrey);

        // Добавление для Boa
        Map<Class<?>, Integer> boaPrey = new HashMap<>();
        boaPrey.put(Fox.class, 15);
        boaPrey.put(Rabbit.class, 20);
        boaPrey.put(Mouse.class, 40);
        boaPrey.put(Duck.class, 10);
        eatProbabilities.put(Boa.class, boaPrey);

        // Добавление для Fox
        Map<Class<?>, Integer> foxPrey = new HashMap<>();
        foxPrey.put(Rabbit.class, 70);
        foxPrey.put(Mouse.class, 90);
        foxPrey.put(Duck.class, 60);
        foxPrey.put(Caterpillar.class, 40);
        eatProbabilities.put(Fox.class, foxPrey);

        // Добавление для Bear
        Map<Class<?>, Integer> bearPrey = new HashMap<>();
        bearPrey.put(Boa.class, 80);
        bearPrey.put(Horse.class, 40);
        bearPrey.put(Deer.class, 80);
        bearPrey.put(Rabbit.class, 80);
        bearPrey.put(Mouse.class, 90);
        bearPrey.put(Goat.class, 70);
        bearPrey.put(Sheep.class, 70);
        bearPrey.put(Boar.class, 50);
        bearPrey.put(Buffalo.class, 20);
        bearPrey.put(Duck.class, 10);
        eatProbabilities.put(Bear.class, bearPrey);

        // Добавление для Eagle
        Map<Class<?>, Integer> eaglePrey = new HashMap<>();
        eaglePrey.put(Fox.class, 10);
        eaglePrey.put(Rabbit.class, 90);
        eaglePrey.put(Mouse.class, 90);
        eaglePrey.put(Duck.class, 80);
        eatProbabilities.put(Eagle.class, eaglePrey);

        // Добавление для Mouse
        Map<Class<?>, Integer> mousePrey = new HashMap<>();
        mousePrey.put(Caterpillar.class, 90);
        mousePrey.put(Grass.class, 100);
        eatProbabilities.put(Mouse.class, mousePrey);

        // Добавление для Boar
        Map<Class<?>, Integer> boarPrey = new HashMap<>();
        boarPrey.put(Mouse.class, 50);
        boarPrey.put(Caterpillar.class, 90);
        boarPrey.put(Grass.class, 100);
        eatProbabilities.put(Boar.class, boarPrey);

        // Добавление для Duck
        Map<Class<?>, Integer> duckPrey = new HashMap<>();
        duckPrey.put(Caterpillar.class, 90);
        duckPrey.put(Grass.class, 100);
        eatProbabilities.put(Duck.class, duckPrey);

        // Добавление для травоядных животных
        Map<Class<?>, Integer> grassPrey = new HashMap<>();
        grassPrey.put(Grass.class, 100);

        eatProbabilities.put(Horse.class, grassPrey);
        eatProbabilities.put(Deer.class, grassPrey);
        eatProbabilities.put(Rabbit.class, grassPrey);
        eatProbabilities.put(Goat.class, grassPrey);
        eatProbabilities.put(Sheep.class, grassPrey);
        eatProbabilities.put(Buffalo.class, grassPrey);
        eatProbabilities.put(Caterpillar.class, grassPrey);

        return eatProbabilities;
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