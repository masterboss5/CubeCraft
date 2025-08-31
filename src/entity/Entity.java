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
    private final EntityType<?> type;
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

    public EntityType<?> getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getRotZ() {
        return rotZ;
    }

    public double getRotY() {
        return rotY;
    }

    public double getRotX() {
        return rotX;
    }

    public boolean hasTicks() {
        return false;
    };

    public void setPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setRotation(double rotX, double rotY, double rotZ) {
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
    }
}
