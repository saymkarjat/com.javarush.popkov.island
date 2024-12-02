package org.simulation;

import lombok.extern.slf4j.Slf4j;
import org.entity.Entity;
import org.island.Cell;
import org.island.IslandMap;
import org.renderer.Renderer;
import org.spawn.*;
import org.spawn.entity_spawners.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
@Slf4j
public class Simulation {
    private AtomicInteger day = new AtomicInteger(0);
    public final int CORE_SIZE_FOR_CELLS = 4;
    public final int CORE_SIZE_FOR_SPAWN = 6;
    public final int CORE_SIZE_FOR_SCHEDULE = 1;
    private List<EntitySpawner<?>> spawners;
    private ExecutorService executorSpawnService;
    private ScheduledExecutorService executorSimulationService;
    private ExecutorService serviceForCells;
    private Renderer renderer;
    IslandMap map;
    private EntitySpawner<Entity> entitySpawner;

    public Simulation(IslandMap map) {
        this.spawners = new ArrayList<>();
        this.executorSpawnService = Executors.newFixedThreadPool(CORE_SIZE_FOR_SPAWN);
        this.map = map;
        this.renderer = new Renderer(map);
        this.entitySpawner = new EntitySpawner<>(map);
        this.executorSimulationService = Executors.newScheduledThreadPool(CORE_SIZE_FOR_SCHEDULE);
        this.serviceForCells = Executors.newFixedThreadPool(CORE_SIZE_FOR_CELLS);
    }

    public void startSimulation(){
        spawnSimulation();
        executorSimulationService.scheduleWithFixedDelay(this::startLifeCycle,0, 2, TimeUnit.SECONDS);
    }

    public void startLifeCycle() {
        Set<Cell> cells = map.getAllCells();
        CountDownLatch latch = new CountDownLatch(cells.size());
        int newDay = day.incrementAndGet();
        log.info("ДЕНЬ №{}", newDay);
        System.out.println("ДЕНЬ №"+newDay);

        for (Cell cell : cells) {
            serviceForCells.submit(() -> {
                try {
                    cell.setMap(map);
                    cell.run();
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Lifecycle interrupted!");
        }
        if (renderer.printCountOfPredators() == 0 || renderer.printCountOfHerbivores() == 0){
            stopSimulation();
        }

        renderer.printStats();
    }

    public void stopSimulation() {
        executorSimulationService.shutdown();
        try {
            if (!executorSimulationService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorSimulationService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorSimulationService.shutdownNow();
        }

        serviceForCells.shutdown();
        try {
            if (!serviceForCells.awaitTermination(10, TimeUnit.SECONDS)) {
                serviceForCells.shutdownNow();
            }
        } catch (InterruptedException e) {
            serviceForCells.shutdownNow();
        }

        log.info("Симуляция завершена");
        System.out.println("Симуляция завершена");
    }

    public void spawnSimulation(){
        addSpawners();
        for (EntitySpawner<?> spawner : spawners) {
            executorSpawnService.submit(spawner);
        }
        executorSpawnService.shutdown();
        try {
            if (!executorSpawnService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorSpawnService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorSpawnService.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println(" SPAWN ");
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
