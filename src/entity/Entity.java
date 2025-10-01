package entity;

import block.Block;
import block.BlockPosition;
import block.Blocks;
import render.RenderSystem;
import util.Axis;
import util.Box;
import util.Hitbox;
import util.collection.Pair;
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
    private double velocityX = 0;
    private double velocityY = 0;
    private double velocityZ = 0;
    private final UUID ID = UUID.randomUUID();
    private final EntityType<?> type;
    private final Box boundingBox = Box.NULL_BOX.get();
    private final Hitbox hitbox;
    private final BlockPosition blockPosition = new BlockPosition();
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

    //TODO optimize
    public void tick() {
        double damping = 0.9; // or 0.95 for gentler slowdown

        this.velocityX *= damping;
        this.velocityZ *= damping;
        double nextX = this.x + this.velocityX;
        double nextY = this.y + this.velocityY;
        double nextZ = this.z + this.velocityZ;
        double bottomYOffset = Math.abs(this.boundingBox.getMinY());

        this.hitbox.moveTo(nextX, nextY, nextZ);
        Hitbox footCollisionHitbox = new Hitbox(new Box(
                this.boundingBox.getMinX(),
                this.boundingBox.getMinY(),
                this.boundingBox.getMinZ(),
                this.boundingBox.getMaxX(),
                this.boundingBox.getMinY(),
                this.boundingBox.getMaxZ()
        ));
        footCollisionHitbox.moveTo(nextX, nextY, nextZ);

        Pair<Block, BlockPosition> footBlock = footCollisionHitbox.intersectsBlock(world, Axis.Y);
        if (footBlock != null) {
            this.velocityY = 0;
            this.y = footBlock.first().getShape().getMaxY() + footBlock.second().getY() + bottomYOffset;
            nextY = this.y;
        } else {
            this.y = nextY;
        }

        this.hitbox.moveTo(nextX, nextY, nextZ);
        Hitbox sideCollisionHitbox = new Hitbox(new Box(
                this.boundingBox.getMinX(),
                this.boundingBox.getMinY(),
                this.boundingBox.getMinZ(),
                this.boundingBox.getMinX(),
                this.boundingBox.getMaxY(),
                this.boundingBox.getMaxZ()
        ));
        sideCollisionHitbox.moveTo(nextX, nextY, nextZ);

        Pair<Block, BlockPosition> sideBlock = sideCollisionHitbox.intersectsBlock(world, Axis.X);
        if (sideBlock != null) {
            this.velocityX = 0;
            this.x = sideBlock.first().getShape().getMaxX() + sideBlock.second().getX() + Math.abs(this.boundingBox.getMinX());;
            nextX = this.x;
        } else {
            this.x = nextX;
        }

        this.hitbox.moveTo(nextX, nextY, nextZ);
        Hitbox frontCollisionHitbox = new Hitbox(new Box(
                this.boundingBox.getMinX(),
                this.boundingBox.getMinY(),
                this.boundingBox.getMinZ(),
                this.boundingBox.getMaxX(),
                this.boundingBox.getMaxY(),
                this.boundingBox.getMinZ()
        ));
        frontCollisionHitbox.moveTo(nextX, nextY, nextZ);

        if (frontCollisionHitbox.intersectsBlock(world, Axis.Z) != null) {
            this.velocityZ = 0;
        } else {
            this.z = nextZ;
        }

        RenderSystem.renderHitbox(footCollisionHitbox.getWorldBox(), 0, -1, 0);
        RenderSystem.renderHitbox(sideCollisionHitbox.getWorldBox(), -1, 0, 0);
        RenderSystem.renderHitbox(frontCollisionHitbox.getWorldBox(), 0, 0, -1);

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

    public double getVelocityZ() {
        return velocityZ;
    }

    public void setVelocityZ(double velocityZ) {
        this.velocityZ = velocityZ;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }
}
