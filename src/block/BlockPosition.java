package block;

import org.joml.Vector3f;
import world.ChunkPosition;

import java.util.Objects;

//TODO make immutable
public class BlockPosition {
    public static final BlockPosition ORIGIN = new BlockPosition();
    private final int x;
    private final int y;
    private final int z;

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

    public BlockPosition setX(int x) {
        return new BlockPosition(x, this.getY(), this.getZ());
    }

    public int getY() {
        return y;
    }

    public BlockPosition setY(int y) {
        return new BlockPosition(this.getX(), y, this.getZ());
    }

    public int getZ() {
        return z;
    }

    public BlockPosition setZ(int z) {
        return new BlockPosition(this.getX(), this.getY(), z);
    }

    public BlockPosition set(int x, int y, int z) {
        return new BlockPosition(x, y, z);
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
        return new BlockPosition(this.getX() + dx, this.getY() + dy, this.getZ() + dz);
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
        if (obj == null || getClass() != obj.getClass()) return false;
        BlockPosition other = (BlockPosition) obj;

        return this.getX() == other.getX() && this.getY() == other.getY() && this.getZ() == other.getZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY(), this.getZ());
    }

    public ChunkPosition toChunkPosition() {
        int chunkX = this.getX() / ChunkPosition.CHUNK_WIDTH;
        int chunkZ = this.getZ() / ChunkPosition.CHUNK_WIDTH;

        if (this.getX() < 0 && this.getX() % ChunkPosition.CHUNK_WIDTH != 0) {
            chunkX = chunkX - 1;
        }

        if (this.getZ() < 0 && this.getZ() % ChunkPosition.CHUNK_WIDTH != 0) {
            chunkZ = chunkZ - 1;
        }

        return new ChunkPosition(chunkX, chunkZ);
    }

    public static BlockPosition fromChunkPosition(ChunkPosition chunkPos) {
        return new BlockPosition(chunkPos.getX() * ChunkPosition.CHUNK_WIDTH, 0, chunkPos.getZ() * ChunkPosition.CHUNK_WIDTH);
    }

    public BlockPosition toLocalChunkPosition() {
        int localX = this.getX() % ChunkPosition.CHUNK_WIDTH;
        int localZ = this.getZ() % ChunkPosition.CHUNK_WIDTH;

        if (localX < 0) localX += ChunkPosition.CHUNK_WIDTH;
        if (localZ < 0) localZ += ChunkPosition.CHUNK_WIDTH;

        return new BlockPosition(localX, this.getY(), localZ);
    }
}
