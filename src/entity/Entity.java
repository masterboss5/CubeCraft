package entity;

import block.Block;
import block.BlockPosition;
import render.RenderSystem;
import util.Box;
import util.Hitbox;
import world.World;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity {
    private double x;
    private double y;
    private double z;
    private double rotX;
    private double rotY;
    private double rotZ;
    private double velocity;
    private double velocityX;
    private double velocityY = -0.7;
    private double velocityZ;
    private BlockPosition blockPosition = new BlockPosition();
    private final UUID ID = UUID.randomUUID();
    private final EntityType<?> type;
    private final Box boundingBox = Box.NULL_BOX.get();
    private final Hitbox hitbox;
    private final World world;
    private boolean isJumping;
    private boolean isFalling;

    public Entity(double x, double y, double z, EntityType<?> type, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.type = type;
        this.world = world;

        this.getType().getModel().setEntityBoundingBox(this.boundingBox);
        this.hitbox = new Hitbox(this.boundingBox);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) return false;
        Entity entity = (Entity) other;
        return Objects.equals(this.getID(), entity.getID()) && Objects.equals(this.type, entity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getID(), this.getType());
    }

    public void tick() {
        this.y += this.velocityY;
        this.hitbox.moveTo(this.x, this.y, this.z);
        double bottomY = this.boundingBox.getMinY();
        this.blockPosition = new BlockPosition(this.x, this.y + bottomY, this.z);

        Hitbox blockBox = new Hitbox(this.world.getBlock(blockPosition).getShape());
        blockBox.moveTo(this.blockPosition.getX(), this.blockPosition.getY(), this.blockPosition.getZ());

        if (this.hitbox.intersects(blockBox)) {
            if (!this.world.getBlock(blockPosition).isAirBlock()) {
                this.onBlockCollision(this.world.getBlock(blockPosition), blockPosition.getX(), blockPosition.getY(), blockPosition.getZ());
                this.velocityY = 0;
                this.y = blockBox.getWorldBox().getMaxY() + Math.abs(this.boundingBox.getMinY());
            }
        }

        RenderSystem.renderHitbox(blockBox.getWorldBox(), 0, 0, 0);
    }

    private void onBlockCollision(Block block, int bx, int by, int bz) {
        System.out.println("COLLISION");
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

    public Box getBoundingBox() {
        return boundingBox;
    }
}
