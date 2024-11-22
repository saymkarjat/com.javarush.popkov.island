package org.settings;

import org.configuration.YamlConfigLoader;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Settings {
    //размер острова
    public static int islandWidth = 2;
    public static int islandHeight = 3;
    static String probabilitiesConfigPath = "config/eat_probabilities/probabilities.yaml";
    //вероятности съедения
    public static Map<?, ?> probabilities = YamlConfigLoader.loadEatProbabilities(probabilitiesConfigPath).entrySet()
            .stream()
            .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, v->Collections.unmodifiableMap(v.getValue())));




}
