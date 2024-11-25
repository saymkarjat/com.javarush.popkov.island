package org.example;


import org.entities.enumerations.EntityType;
import org.entities.predators.Wolf;
import org.fabric.CloneEntityFactory;
import org.island.Cell;
import org.island.Coordinate;
import org.island.IslandMap;
import org.renderer.Renderer;
import org.settings.Settings;
import org.simulation.Simulation;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        IslandMap map = new IslandMap(Settings.islandWidth, Settings.islandHeight);
        Simulation simulation = new Simulation(map);
        simulation.startSimulation();
    }
}
