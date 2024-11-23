package org.settings;

import org.configuration.YamlConfigLoader;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Settings {
    //размер острова
    public static int islandWidth = 5;
    public static int islandHeight = 5;
    static String probabilitiesConfigPath = "config/eat_probabilities/probabilities.yaml";
    //вероятности съедения
    public final static Map<?, ?> probabilities = YamlConfigLoader.loadEatProbabilities(probabilitiesConfigPath);




}
