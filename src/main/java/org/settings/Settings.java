package org.settings;

import org.configuration.YamlConfigLoader;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Settings {
    //размер острова
    public static int islandWidth = 10;
    public static int islandHeight = 10;
    static String probabilitiesConfigPath = "config/eat_probabilities/probabilities.yaml";
    //вероятности съедения
    public final static Map<String, Map<String, Integer>> probabilities = YamlConfigLoader.loadEatProbabilities(probabilitiesConfigPath);




}
