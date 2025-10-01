package block;

import org.joml.Vector3f;
import world.Chunk;
import world.ChunkPosition;

import java.util.Objects;

public class BlockPosition {
    public static final BlockPosition ORIGIN = new BlockPosition();
    private int x;
    private int y;
    private int z;

    public BlockPosition(double x, double y, double z) {
        this((int) x, (int) y, (int) z);
    }

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
        int dx = this.getX() - other.getX();
        int dy = this.getY() - other.getY();
        int dz = this.getZ() - other.getZ();

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public double distanceToOrigin() {
        return this.distanceTo(ORIGIN);
    }

    public BlockPosition offset(int dx, int dy, int dz) {
        return new BlockPosition(this.x + dx, this.y + dy, this.z + dz);
    }

    public boolean isAlignedX(BlockPosition other) {
        return this.getY() == other.getY() && this.getZ() == other.getZ();
    }

    public boolean isAlignedY(BlockPosition other) {
        return this.getX() == other.getX() && this.getZ() == other.getZ();
    }

    public boolean isAlignedZ(BlockPosition other) {
        return this.getX() == other.getX() && this.getY() == other.getY();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        BlockPosition other = (BlockPosition) obj;

        return this.getX() == other.getX() && this.getY() == other.getY() && this.getZ() == other.getZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY(), this.getZ());
    }

    @Override
    public String toString() {
        return "BlockPosition{" +
                "x=" + this.x +
                ", y=" + this.y +
                ", z=" + this.z +
                '}';
    }

    public ChunkPosition toChunkPosition() {
        int chunkX = this.getX() / Chunk.CHUNK_WIDTH;
        int chunkZ = this.getZ() / Chunk.CHUNK_WIDTH;

        if (this.getX() < 0 && this.getX() % Chunk.CHUNK_WIDTH != 0) {
            chunkX = chunkX - 1;
        }

        if (this.getZ() < 0 && this.getZ() % Chunk.CHUNK_WIDTH != 0) {
            chunkZ = chunkZ - 1;
        }

        return new ChunkPosition(chunkX, chunkZ);
    }

    public static BlockPosition fromChunkPosition(ChunkPosition chunkPos) {
        return new BlockPosition(chunkPos.getX() * Chunk.CHUNK_WIDTH, 0, chunkPos.getZ() * Chunk.CHUNK_WIDTH);
    }

    public BlockPosition toLocalChunkPosition() {
        int localX = this.getX() % Chunk.CHUNK_WIDTH;
        int localZ = this.getZ() % Chunk.CHUNK_WIDTH;

        if (localX < 0) localX += Chunk.CHUNK_WIDTH;
        if (localZ < 0) localZ += Chunk.CHUNK_WIDTH;

        return new BlockPosition(localX, this.getY(), localZ);
    }
}
