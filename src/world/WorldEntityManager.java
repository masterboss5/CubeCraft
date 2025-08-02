package world;

import entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WorldEntityManager {
    private final Map<UUID, Entity> tickingEntities = new HashMap<>();
    private final Map<UUID, Entity> entityMap = new HashMap<>();
    private final World world;

    public WorldEntityManager(World world) {
        this.world = world;
    }

    protected void addEntity(Entity entity) {
        this.entityMap.put(entity.getID(), entity);

        if (entity.hasTicks()) {
            this.tickingEntities.put(entity.getID(), entity);
        }
    }

    protected void removeEntity(Entity entity) {
        this.entityMap.remove(entity.getID());
        this.tickingEntities.remove(entity.getID());
    }

    protected Entity getEntity(UUID uuid) {
        return this.entityMap.get(uuid);
    }

    protected int getEntityCount() {
        return this.entityMap.size();
    }

    protected int getTickableEntityCount() {
        return this.tickingEntities.size();
    }

    protected void tickEntities() {
        this.tickingEntities.values().forEach(Entity::tick);
    }
}
