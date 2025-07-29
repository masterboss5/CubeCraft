package entity;

import block.BlockPosition;
import util.Box;
import world.World;

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
    UUID ID = new UUID(0L, 0L);
    private Box hitbox;

    public Entity(double x, double y, double z, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void remove() {

    }

    public void unload() {

    }
}
