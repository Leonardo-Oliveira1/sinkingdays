package main;

import entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    private final List<Entity> entities = new ArrayList<>();

    public void add(Entity entity) {
        entities.add(entity);
    }

    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }
    }
}
