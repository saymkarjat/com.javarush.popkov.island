package org.simulation;

import org.entities.Entity;
import org.island.IslandMap;
import org.renderer.Renderer;
import org.settings.Settings;
import org.spawn.*;
import org.spawn.entity_spawners.*;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private List<EntitySpawner<?>> spawners;
    private ExecutorService executorService;
    private Renderer renderer;
    IslandMap map;
    private EntitySpawner<Entity> entitySpawner;

    public Simulation() {
        this.spawners = new ArrayList<>();
        this.executorService = Executors.newFixedThreadPool(6);
        this.map = new IslandMap(Settings.islandWidth, Settings.islandHeight);
        this.renderer = new Renderer(map);
        this.entitySpawner = new EntitySpawner<>(map);
    }
    public void spawnSimulation(){
        addSpawners();
        for (EntitySpawner<?> spawner : spawners) {
            executorService.submit(spawner);
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
        renderer.printStats();
    }

    private void addSpawners(){
        spawners.add(new BuffaloSpawner(map));
        spawners.add(new BearSpawner(map));
        spawners.add(new HorseSpawner(map));
        spawners.add(new DeerSpawner(map));
        spawners.add(new BoarSpawner(map));
        spawners.add(new SheepSpawner(map));
        spawners.add(new GoatSpawner(map));
        spawners.add(new WolfSpawner(map));
        spawners.add(new BoaSpawner(map));
        spawners.add(new FoxSpawner(map));
        spawners.add(new EagleSpawner(map));
        spawners.add(new RabbitSpawner(map));
        spawners.add(new DuckSpawner(map));
        spawners.add(new MouseSpawner(map));
        spawners.add(new CaterpillarSpawner(map));
        spawners.add(new GrassSpawner(map));

    }
}
