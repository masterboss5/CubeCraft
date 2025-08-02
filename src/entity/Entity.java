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
    private Box hitbox;

    public Entity(double x, double y, double z, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) return false;
        Entity entity = (Entity) other;
        return Objects.equals(this.getID(), entity.getID());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getID());
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

    public abstract boolean hasTicks();
}
