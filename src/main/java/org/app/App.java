package org.app;


import org.island.IslandMap;
import org.settings.Settings;
import org.simulation.Simulation;

public class App {
    public static void main(String[] args) {
        IslandMap map = new IslandMap(Settings.islandWidth, Settings.islandHeight);
        Simulation simulation = new Simulation(map);
        simulation.startSimulation();
    }
}
