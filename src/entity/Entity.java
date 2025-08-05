package entity;

import block.BlockPosition;
import util.Box;
import world.World;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity {
    private double x;
    private double y;
    private double z;
    private BlockPosition blockPosition = new BlockPosition();
    private double rotX;
    private double rotY;
    private double rotZ;
    private double velocity;
    private double velocityX;
    private double velocityY;
    private double velocityZ;
    boolean isFalling;
    private final UUID ID = UUID.randomUUID();
    private final EntityType<? extends Entity> type;
    private Box hitbox;
    private final World world;

    public Entity(double x, double y, double z, EntityType<?> type, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        this.world = world;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(this.getID(), entity.getID()) && Objects.equals(this.type, entity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getID(), this.type);
    }

    public void tick() {
    }

    public void destroy() {
    }

    public void unload() {
    }

    public UUID getID() {
        return ID;
    }

    public EntityType<? extends Entity> getType() {
        return type;
    }

    public abstract boolean hasTicks();
}
