package block;

import org.joml.Vector3f;
import world.ChunkPosition;

import java.util.Objects;

public class BlockPosition {
    public static final BlockPosition ORIGIN = new BlockPosition();
    private int x;
    private int y;
    private int z;

    public BlockPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BlockPosition() {
        this(0, 0, 0);
    }

    public Vector3f toVector3f() {
        return new Vector3f(this.getX(), this.getY(), this.getZ());
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void set(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double distanceTo(BlockPosition other) {
        int dx = this.x - other.x;
        int dy = this.y - other.y;
        int dz = this.z - other.z;

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public double distanceToOrigin() {
        return this.distanceTo(ORIGIN);
    }

    public void offset(int dx, int dy, int dz) {
        this.set(this.x + dx, this.y + dy, this.z + dz);
    }

    public boolean isAlignedX(BlockPosition other) {
        return this.y == other.y && this.z == other.z;
    }

    public boolean isAlignedY(BlockPosition other) {
        return this.x == other.x && this.z == other.z;
    }

    public boolean isAlignedZ(BlockPosition other) {
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BlockPosition other = (BlockPosition) obj;

        return this.x == other.x && this.y == other.y && this.z == other.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY(), this.getZ());
    }

    public ChunkPosition toChunkPosition() {
        int chunkX = this.x / ChunkPosition.CHUNK_WIDTH;
        int chunkZ = this.z / ChunkPosition.CHUNK_WIDTH;

        if (this.x < 0 && this.x % ChunkPosition.CHUNK_WIDTH != 0) {
            chunkX = chunkX - 1;
        }

        if (this.z < 0 && this.z % ChunkPosition.CHUNK_WIDTH != 0) {
            chunkZ = chunkZ - 1;
        }

        return new ChunkPosition(chunkX, chunkZ);
    }

    public static BlockPosition fromChunkPosition(ChunkPosition chunkPos) {
        return new BlockPosition(chunkPos.getX() * ChunkPosition.CHUNK_WIDTH, 0, chunkPos.getZ() * ChunkPosition.CHUNK_WIDTH);
    }
}
