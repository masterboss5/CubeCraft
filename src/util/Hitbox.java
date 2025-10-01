package util;

import block.Block;
import block.BlockPosition;
import util.collection.Pair;
import world.World;

public class Hitbox {
    private final Box localBox;
    private final Box worldBox;

    public Hitbox(Box localBox) {
        this.localBox = localBox;
        this.worldBox = Box.NULL_BOX.get();
    }

    public void moveTo(double x, double y, double z) {
        this.worldBox.setPosition(
                this.localBox.getMinX() + x, this.localBox.getMinY() + y, this.localBox.getMinZ() + z,
                this.localBox.getMaxX() + x, this.localBox.getMaxY() + y, this.localBox.getMaxZ() + z
        );
    }

    public boolean intersects(Hitbox other) {
        return this.worldBox.intersects(other.getWorldBox());
    }

    public boolean intersectsX(Hitbox other) {
        return this.worldBox.intersectsX(other.getWorldBox());
    }

    public boolean intersectsY(Hitbox other) {
        return this.worldBox.intersectsY(other.getWorldBox());
    }

    public boolean intersectsZ(Hitbox other) {
        return this.worldBox.intersectsZ(other.getWorldBox());
    }

    //TODO optimize
    public Pair<Block, BlockPosition> intersectsBlock(World world) {
        int minX = (int) Math.floor(this.worldBox.getMinX());
        int minY = (int) Math.floor(this.worldBox.getMinY());
        int minZ = (int) Math.floor(this.worldBox.getMinZ());
        int maxX = (int) Math.floor(this.worldBox.getMaxX());
        int maxY = (int) Math.floor(this.worldBox.getMaxY());
        int maxZ = (int) Math.floor(this.worldBox.getMaxZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = world.getBlock(new BlockPosition(x, y, z));
                    if (block != null && !block.isAirBlock()) {
                        Hitbox hitbox = new Hitbox(block.getShape());
                        hitbox.moveTo(x, y, z);

                        if (this.intersects(hitbox)) {
                            return Pair.of(block, new BlockPosition(x, y, z));
                        }
                    }
                }
            }
        }

        return null;
    }

    public Pair<Block, BlockPosition> intersectsBlock(World world, Axis axis) {
        int minX = (int) Math.floor(this.worldBox.getMinX());
        int minY = (int) Math.floor(this.worldBox.getMinY());
        int minZ = (int) Math.floor(this.worldBox.getMinZ());
        int maxX = (int) Math.floor(this.worldBox.getMaxX());
        int maxY = (int) Math.floor(this.worldBox.getMaxY());
        int maxZ = (int) Math.floor(this.worldBox.getMaxZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = world.getBlock(new BlockPosition(x, y, z));
                    if (block != null && !block.isAirBlock()) {
                        Hitbox hitbox = new Hitbox(block.getShape());
                        hitbox.moveTo(x, y, z);

                        boolean intersects = switch (axis) {
                            case X -> this.intersectsX(hitbox);
                            case Y -> this.intersectsY(hitbox);
                            case Z -> this.intersectsZ(hitbox);
                        };

                        if (intersects) {
                            return Pair.of(block, new BlockPosition(x, y, z));
                        }
                    }
                }
            }
        }

        return null;
    }

    public Box getWorldBox() {
        return worldBox;
    }

    public Box getLocalBox() {
        return localBox;
    }
}
