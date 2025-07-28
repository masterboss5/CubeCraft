package world;

import block.BlockPosition;
import org.joml.Vector3f;

import java.util.Objects;

public final class ChunkPosition {
    public static final ChunkPosition ORIGIN = new ChunkPosition();
    private final int x;
    private final int z;

    public ChunkPosition(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public ChunkPosition() {
        this(0, 0);
    }

    public Vector3f toVector3f() {
        return new Vector3f(x, 0, z);
    }

    public int getX() {
        return x;
    }

    public ChunkPosition setX(int x) {
        return new ChunkPosition(x, this.getZ());
    }

    public int getZ() {
        return z;
    }

    public ChunkPosition setZ(int z) {
        return new ChunkPosition(this.getX(), z);
    }

    public ChunkPosition set(int x, int z) {
        return new ChunkPosition(x, z);
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
        if (obj == null || this.getClass() != obj.getClass()) return false;
        ChunkPosition other = (ChunkPosition) obj;

        return this.x == other.x && this.z == other.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getZ());
    }

    public BlockPosition toBlockPosition() {
        return new BlockPosition(this.x * Chunk.CHUNK_WIDTH, 0, this.z * Chunk.CHUNK_WIDTH);
    }

    public static ChunkPosition fromBlockPosition(BlockPosition blockPos) {
        int chunkX = blockPos.getX() / Chunk.CHUNK_WIDTH;
        int chunkZ = blockPos.getZ() / Chunk.CHUNK_WIDTH;

        if (blockPos.getX() < 0 && blockPos.getX() % Chunk.CHUNK_WIDTH != 0) {
            chunkX = chunkX - 1;
        }

        if (blockPos.getZ() < 0 && blockPos.getZ() % Chunk.CHUNK_WIDTH != 0) {
            chunkZ = chunkZ - 1;
        }

        return new ChunkPosition(chunkX, chunkZ);
    }
}
