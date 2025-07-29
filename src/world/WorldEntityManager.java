package world;

import entity.Entity;

import java.util.ArrayList;

public class WorldEntityManager {
    private final ArrayList<Entity> tickingEntities = new ArrayList<>();
    private final World world;

    public WorldEntityManager(World world) {
        this.world = world;
    }

    protected void addEntity(Entity entity) {

    }
}
