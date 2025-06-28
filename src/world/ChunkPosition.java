package world;

import block.BlockPosition;

import java.util.Objects;

public final class ChunkPosition {
    public static final ChunkPosition ORIGIN = new ChunkPosition();
    public static final int CHUNK_HEIGHT = 128;
    public static final int CHUNK_WIDTH = 16;
    private int x;
    private int z;

    public ChunkPosition(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public ChunkPosition() {
        this(0, 0);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void set(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public double distanceTo(ChunkPosition other) {
        int dx = this.x - other.x;
        int dz = this.z - other.z;

        return Math.sqrt(dx * dx + dz * dz);
    }

    public double distanceToOrigin() {
        return this.distanceTo(ORIGIN);
    }

    public void offset(int dx, int dz) {
        this.set(this.x + dx, this.z + dz);
    }

    public boolean isAlignedX(ChunkPosition other) {
        return this.z == other.z;
    }

    public boolean isAlignedZ(ChunkPosition other) {
        return this.x == other.x;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChunkPosition other = (ChunkPosition) obj;

        return this.x == other.x && this.z == other.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getZ());
    }

    public BlockPosition toBlockPosition() {
        return new BlockPosition(this.x * CHUNK_WIDTH, 0, this.z * CHUNK_WIDTH);
    }

    public static ChunkPosition fromBlockPosition(BlockPosition blockPos) {
        int chunkX = blockPos.getX() / CHUNK_WIDTH;
        int chunkZ = blockPos.getZ() / CHUNK_WIDTH;

        if (blockPos.getX() < 0 && blockPos.getX() % CHUNK_WIDTH != 0) {
            chunkX = chunkX - 1;
        }

        if (blockPos.getZ() < 0 && blockPos.getZ() % CHUNK_WIDTH != 0) {
            chunkZ = chunkZ - 1;
        }

        return new ChunkPosition(chunkX, chunkZ);
    }
}
